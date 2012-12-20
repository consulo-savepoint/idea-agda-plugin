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
import org.jetbrains.agda.psi.impl.ANameImpl
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
        for (cDeclaration : CDeclaration? in myDeclarations.values())
        {
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
        makeContext(body.left, null);
        typeCheck(body.right)
    }

    fun makeContext(expr : CExpression, aType : CExpression?) : CContext {
        when (expr) {
            is CRefExpression -> {
                expr.name
                return CContext(HashMap<String, CExpression>())
            }
            is CApplication -> {
                return makeContext(expr.right, null);
            }
            else -> {
                return CContext(HashMap<String, CExpression>())
            }
        }
    }

    public fun typeCheck(expression : CExpression) : CExpression? {
        expression.setType(doInferType(CContext(HashMap<String, CExpression>()), expression))
        return expression.getType()
    }

    private fun unifyExpressions(context : CContext, left : CExpression, right : CExpression) : List<Assignment> {
        if (left is CRefExpression) {
            if (right is CRefExpression) {
                if (context.map.containsKey(left.name)) {
                    return Arrays.asList(Assignment(left.name, right))
                }
                if (context.map.containsKey(right.name)) {
                    return Arrays.asList(Assignment(right.name, left))
                }
                if (left.declaration == right.declaration) {
                    return ArrayList()
                }
            }
        }
        if (right is CImplicitArrowExpression) {
            val implicitArrowExpression : CImplicitArrowExpression = right
            return unifyExpressions(context.put(implicitArrowExpression.name, implicitArrowExpression.left), left, implicitArrowExpression.right)
        }
        if (left is CApplication) {
            if (right is CApplication) {
                val l1 = unifyExpressions(context, left.left, right.left)
                val l2 = unifyExpressions(context, left.right, right.right)
                val result = ArrayList<Assignment>()
                result.addAll(l1)
                result.addAll(l2)
                return result
            }
        }
        throw RuntimeException();
    }

    private fun applyAssignments(expression : CExpression, assignments : List<Assignment>) : CExpression {
        return expression.transform(object : ExpressionTransformer() {

            public override fun transformRef(cref: CRefExpression): CExpression {
                val filtered = assignments.filter { it -> it.name == cref.name }
                if (!filtered.isEmpty()) {
                    return filtered.first().value;
                }
                return cref;
            }
        })
    }

    private fun doCheckApplication(context : CContext, left : CExpression, right : CExpression) : CExpression? {
        if (left is CArrowExpression) {
            val assignments = unifyExpressions(context, left.left, right);
            return applyAssignments(left.right, assignments);
        } else if (left is CPiArrowExpression) {
            val piArrowExpression : CPiArrowExpression = left;
        } else if (left is CImplicitArrowExpression) {
            val implicitArrowExpression : CImplicitArrowExpression = left;
            return doCheckApplication(context.put(implicitArrowExpression.name, implicitArrowExpression.left), implicitArrowExpression.right, right);
        }
        return null
    }
    private fun doInferType(context : CContext, expression : CExpression) : CExpression? {
        if (expression is CApplication) {
            val application : CApplication = expression
            val leftType : CExpression = typeCheck(application.left)!!
            val rightType : CExpression = typeCheck(application.right)!!
            return doCheckApplication(context, leftType, rightType);
        } else if (expression is CRefExpression) {
                var declaration : Any = expression.declaration
                val cDeclaration : CDeclaration? = myDeclarations.get(declaration)
                if (cDeclaration != null) {
                    return cDeclaration.aType
                } else {
                    //return deduceType(myExpressions.get(declaration))
                }
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
