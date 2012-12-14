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
                    check(functionDeclaration, body)
                }
            }

        }
    }
    private open fun check(functionDeclaration : CFunctionDeclaration?, body : FunctionBody?) : Unit {
        typeCheck(body?.myRight!!)
    }
    public fun typeCheck(expression : CExpression) : CExpression? {
        expression.setType(doTypeCheck(CContext(HashMap<String, CExpression>()), expression))
        return expression.getType()
    }
    private fun doCheckApplication(context : CContext, left : CExpression, right : CExpression) : CExpression? {
        if (left is CArrowExpression) {
            return left.right;
        } else if (left is CPiArrowExpression) {
            val piArrowExpression : CPiArrowExpression = left;
        } else if (left is CImplicitArrowExpression) {
            val implicitArrowExpression : CImplicitArrowExpression = left;
            context.put(implicitArrowExpression.name, implicitArrowExpression.left);
        }
        return null
    }
    private fun doTypeCheck(context : CContext, expression : CExpression) : CExpression? {
        if (expression is CApplication) {
            val application : CApplication = expression
            val leftType : CExpression = typeCheck(application.left)!!
            val rightType : CExpression = typeCheck(application.right)!!
            return doCheckApplication(context, leftType, rightType);
        } else if (expression is CRefExpression) {
                var declaration : Any = expression.declaration
                var cDeclaration : CDeclaration? = myDeclarations.get(declaration)
                if (cDeclaration != null) {
                    return cDeclaration.aType
                } else {
                    //return deduceType(myExpressions.get(declaration))
                }
            }

        return null
    }

    private open fun deduceType(expression : CExpression?) : CExpression? {
        var parent : CExpression? = expression?.getParent()
        if ((parent is CApplication?))
        {
            var application : CApplication? = (parent as CApplication?)
            if (application!!.right == expression)
            {
                var leftType : CExpression? = null;
                while ((leftType is CImplicitArrowExpression?))
                {
                    leftType = ((leftType as CImplicitArrowExpression?))?.right
                }
                if ((leftType is CArrowExpression?))
                {
                    return ((leftType as CArrowExpression?))?.left
                }

                return null
            }

        }

        return null
    }

    public fun getTypeOf(elementAt : T) : CExpression? {
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
