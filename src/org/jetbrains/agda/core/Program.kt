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
import org.jetbrains.agda.core.expression.CMetaVariable

/**
 * @author Evgeny.Kurbatsky
 */
public open class Program<T>() {
    val myDeclarations : MutableMap<T, CDeclaration> = LinkedHashMap<T, CDeclaration>()
    val myExpressions : MutableMap<T, CExpression?> = HashMap<T, CExpression?>()

    private open fun checkTypes() : Unit {
        for (cDeclaration : CDeclaration? in myDeclarations.values()) {
            if (cDeclaration is CFunctionDeclaration) {
                val functionDeclaration : CFunctionDeclaration = cDeclaration
                for (body in functionDeclaration.getBodyes()) {
                    check(body)
                }
            }

        }
    }

    public fun calculateType(expression : CExpression) : CExpression? {
        val signature = EquationsContainer()
        var result = checkType(emptyContext, signature, expression, null, false)

        for (rule in signature.rules) {
            result = replaceVar(result, rule.name, rule.value)
        }

        return result;
    }

    private open fun check(body : CFunctionBody) : Unit {
        val signature = EquationsContainer()
        val resultType = checkType(emptyContext, signature, body.left, null, true)

        checkType(emptyContext, signature, body.right, resultType, false)
    }

    public fun checkType(context : CContext, signature : EquationsContainer, expression : CExpression,  aType : CExpression?, leftPart : Boolean) : CExpression {
        val result = doCheckType(context, signature, expression, aType, leftPart)
        expression.setType(result)
        return result;
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


    private fun unifyExpressions(context : CContext, left : CExpression, right : CExpression, signature : EquationsContainer) {
        if (left is CRefExpression) {
            if (right is CRefExpression) {
                if (signature.matavariables.containsKey(left.name)) {
                    signature.rules.add(Rule(left.name, right))
                    return
                }
                if (signature.matavariables.containsKey(right.name)) {
                    signature.rules.add(Rule(right.name, left))
                    return
                }
                if (left.declaration == right.declaration) {
                    return
                }
            }
        }
        if (left is CImplicitArrowExpression) {
            val newLeft = replaceVar(left.right, left.name, signature.nextVarRef(left.name, left.left))
            return unifyExpressions(context, newLeft, right, signature);
        }
        if (right is CImplicitArrowExpression) {
            val newRight = replaceVar(right.right, right.name, signature.nextVarRef(right.name, right.left))
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


    private fun doCheckApplication(context : CContext, left : CExpression, rightExpression : CExpression, signature : EquationsContainer, leftPart : Boolean) : CExpression {
        if (left is CArrowExpression) {
            doCheckType(context, signature, rightExpression, left.left, leftPart)
            return left.right;
        } else if (left is CPiArrowExpression) {
            val piArrowExpression : CPiArrowExpression = left;
            throw RuntimeException()
        } else if (left is CImplicitArrowExpression) {
            val expression = replaceVar(left.right, left.name, signature.nextVarRef(left.name, left.left))
            return doCheckApplication(context, expression, rightExpression, signature, leftPart);
        }
        throw RuntimeException()
    }

    private fun doCheckType(context : CContext, signature : EquationsContainer, expression : CExpression, aType : CExpression?, leftPart : Boolean) : CExpression {
        if (expression is CApplication) {
            val application : CApplication = expression
            val leftType : CExpression = checkType(context, signature, application.left, null, leftPart)
            return doCheckApplication(context, leftType, application.right, signature, leftPart);
        } else if (expression is CRefExpression) {
            if (context.get(expression.name) != null) {
                return context.get(expression.name)!!
            }
            var declaration : Any = expression.declaration
            val cDeclaration : CDeclaration? = myDeclarations.get(declaration)
            if (cDeclaration != null) {
                return cDeclaration.aType
            }
            val functionsVar = signature.variables.get(expression.name)
            if (functionsVar != null) {
                return functionsVar
            }
            if (leftPart) {
                signature.variables.put(expression.name, aType!!)
                return aType
            }
            throw RuntimeException()
        }
        if (expression is CMetaVariable) {
            return aType!!;
        }
        throw RuntimeException()
    }

    public fun getTypeOf(elementAt : T) : CExpression? {
        checkTypes()
        val expression = myExpressions.get(elementAt)

        return expression?.getType()
    }

    public open fun printDebug() : Unit {
        for (declaration : CDeclaration? in myDeclarations.values()) {
            println(declaration?.toString())
        }
    }
}
