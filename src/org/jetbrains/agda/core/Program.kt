package org.jetbrains.agda.core

import com.intellij.psi.PsiElement
import java.util.LinkedHashMap
import java.util.HashMap
import org.jetbrains.agda.psi.DataDeclaration
import com.intellij.psi.PsiFile
import org.jetbrains.agda.psi.Expression
import org.jetbrains.agda.psi.FunctionDeclaration
import org.jetbrains.agda.psi.Application
import org.jetbrains.agda.mixfix.TreeElement
import org.jetbrains.agda.mixfix.Grammar
import java.util.Arrays
import org.codehaus.groovy.ast.expr.ExpressionTransformer
import java.util.ArrayList
import java.nio.charset.CoderResult

/**
 * @author Evgeny.Kurbatsky
 */
public open class Program<T>() {
    val myDeclarations : MutableMap<T, CDeclaration> = LinkedHashMap<T, CDeclaration>()
    val myExpressions : MutableMap<T, CExpression?> = HashMap<T, CExpression?>()
    var myLassDeclaration : CFunctionDeclaration? = null

    private open fun checkTypes() : Unit {
        for (cDeclaration : CDeclaration? in myDeclarations.values()) {
            if (cDeclaration is CFunctionDeclaration) {
                var functionDeclaration : CFunctionDeclaration = cDeclaration
                for (body : FunctionBody? in functionDeclaration.getBodyes()!!)
                {
                    check(functionDeclaration, body!!)
                }
            }

        }
    }
    private open fun check(functionDeclaration : CFunctionDeclaration, body : FunctionBody) : Unit {
        val signature = Signature()
        val contextPair = inferTypes(body.left, null, signature);
        val calculatedType = calculateType(contextPair.first, body.right, signature)
    }

    fun inferTypes(expr : CExpression, val aType : CExpression?, signature : Signature) : Pair<CContext, CExpression> {
        when (expr) {
            is CRefExpression -> {
                var declaration : Any = expr.declaration
                val cDeclaration : CDeclaration? = myDeclarations.get(declaration)
                if (cDeclaration != null) {
                    return Pair(emptyContext, cDeclaration.aType)
                }
                if (aType != null) {
                    return Pair(emptyContext.put(expr.name, aType), aType)
                } else {
                    throw RuntimeException();
                }
            }
            is CApplication -> {
                val leftContextPair = inferTypes(expr.left, null, signature)
                val leftType = leftContextPair.second
                if (leftType is CArrowExpression) {
                    val rightContext = inferTypes(expr.right, leftType.left, signature)
                    return Pair(leftContextPair.first.merge(rightContext.first), leftType.right);
                }
                throw RuntimeException();
            }
            else -> {
                throw RuntimeException();
            }
        }
    }

    public fun calculateType(expression : CExpression) : CExpression? {
        val signature = Signature()
        var calculatedType = calculateType(emptyContext, expression, signature)!!
        for (val rule in signature.rules) {
            calculatedType = replaceVar(calculatedType, rule.name, rule.value);
        }
        return calculatedType;
    }


    public fun calculateType(context : CContext, expression : CExpression, signature : Signature) : CExpression? {
        expression.setType(doCalculateType(context, expression, signature))
        return expression.getType()
    }


    fun replaceVar(expr : CExpression, name : String, value : CExpression) =
        expr.transform(object : ExpressionTransformer() {
            public override fun transformRef(cref: CRefExpression): CExpression {
                if (cref.name == name) {
                    return value;
                } else {
                    return super<ExpressionTransformer>.transformRef(cref)
                }
            }
        })


    private fun unifyExpressions(context : CContext, left : CExpression, right : CExpression, signature : Signature) {
        if (left is CRefExpression) {
            if (right is CRefExpression) {
                if (signature.variables.containsKey(left.name)) {
                    signature.rules.add(Rule(left.name, right))
                    return
                }
                if (signature.variables.containsKey(right.name)) {
                    signature.rules.add(Rule(right.name, left))
                    return
                }
                if (left.declaration == right.declaration) {
                    return
                }
            }
        }
        if (left is CImplicitArrowExpression) {
            val newLeft = replaceVar(left.right, left.name, signature.nextVarRef(left.left))
            return unifyExpressions(context, newLeft, right, signature);
        }
        if (right is CImplicitArrowExpression) {
            val newRight = replaceVar(right.right, right.name, signature.nextVarRef(right.left))
            return unifyExpressions(context, left, newRight, signature)
        }
        if (left is CApplication) {
            if (right is CApplication) {
                unifyExpressions(context, left.left, right.left, signature)
                unifyExpressions(context, left.right, right.right, signature)
                return
            }
        }
        throw RuntimeException();
    }


    private fun doCheckApplication(context : CContext, left : CExpression, right : CExpression, signature : Signature) : CExpression? {
        if (left is CArrowExpression) {
            unifyExpressions(context, left.left, right, signature);
            return left.right;
        } else if (left is CPiArrowExpression) {
            val piArrowExpression : CPiArrowExpression = left;

        } else if (left is CImplicitArrowExpression) {
            val implicitArrowExpression : CImplicitArrowExpression = left;
            val expression = replaceVar(implicitArrowExpression.right, implicitArrowExpression.name, signature.nextVarRef(implicitArrowExpression.left))
            return doCheckApplication(context, expression, right, signature);
        }
        throw RuntimeException()
    }

    private fun doCalculateType(context : CContext, expression : CExpression, signature : Signature) : CExpression? {
        if (expression is CApplication) {
            val application : CApplication = expression
            val leftType : CExpression = calculateType(context, application.left, signature)!!
            val rightType : CExpression = calculateType(context, application.right, signature)!!
            return doCheckApplication(context, leftType, rightType, signature);
        } else if (expression is CRefExpression) {
            if (context.get(expression.name) != null) {
                return context.get(expression.name)
            }
            var declaration : Any = expression.declaration
            val cDeclaration : CDeclaration? = myDeclarations.get(declaration)
            if (cDeclaration != null) {
                return cDeclaration.aType
            }
            throw RuntimeException()
        }

        return null
    }

    public fun getTypeOf(elementAt : T) : CExpression? {
        checkTypes()
        var expression : CExpression? = myExpressions.get(elementAt)
        if (expression != null)
        {
            return expression?.getType()
        }

        return null
    }
    public open fun printDebug() : Unit {
        for (declaration : CDeclaration? in myDeclarations.values()) {
            println(declaration?.toString())
        }
    }
}
