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
public open class Program() {
    val myDeclarations : MutableMap<PsiElement, CDeclaration> = LinkedHashMap<PsiElement, CDeclaration>()
    val myExpressions : MutableMap<PsiElement?, CExpression?> = HashMap<PsiElement?, CExpression?>()
    val myVariables : MutableMap<String?, CExpression?> = HashMap<String?, CExpression?>()
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
        typeCheck(body?.myRight)
    }
    private open fun typeCheck(expression : CExpression?) : CExpression? {
        expression?.setType(doTypeCheck(CContext(HashMap<String, CExpression>()), expression))
        return expression?.getType()
    }
    private open fun doTypeCheck(context : CContext?, expression : CExpression?) : CExpression? {
        if ((expression is CApplication?)) {
            var application : CApplication? = (expression as CApplication?)
            val leftType : CExpression? = typeCheck(application?.getLeft())
            var rightType : CExpression? = typeCheck(application?.getRight())
            if (leftType is CArrowExpression) {
                return leftType.getRight()
            }
            else
                if ((leftType is CPiArrowExpression?))
                {
                    var piArrowExpression : CPiArrowExpression? = (leftType as CPiArrowExpression?)
                }
                else
                    if ((leftType is CImplicitArrowExpression?))
                    {
                        var implicitArrowExpression : CImplicitArrowExpression? = (leftType as CImplicitArrowExpression?)
                        doTypeCheck(context?.put(implicitArrowExpression?.getName()!!, implicitArrowExpression?.getLeft()!!), implicitArrowExpression?.getRight())
                    }

        }
        else
            if (expression is CRefExpression) {
                var declaration : PsiElement? = expression.getDeclaration()
                var cDeclaration : CDeclaration? = myDeclarations?.get(declaration)
                if (cDeclaration != null)
                {
                    return cDeclaration?.getType()
                }
                else
                {
                    return deduceType(myExpressions?.get(declaration))
                }
            }

        return null
    }
    private open fun getTypeOf(expression : CExpression?) : CExpression? {
        if ((expression is CRefExpression?))
        {
            var declaration : PsiElement? = ((expression as CRefExpression?))?.getDeclaration()
            var cDeclaration : CDeclaration? = myDeclarations?.get(declaration)
            if (cDeclaration != null)
            {
                return cDeclaration?.getType()
            }
            else
            {
                return deduceType(myExpressions?.get(declaration))
            }
        }

        if ((expression is CApplication?)) {
            val typeOf : CExpression? = getTypeOf(((expression as CApplication?))?.getLeft())
            if (typeOf is CArrowExpression) {
                return typeOf.getRight();
            }

        }

        return null
    }
    private open fun deduceType(expression : CExpression?) : CExpression? {
        var parent : CExpression? = expression?.getParent()
        if ((parent is CApplication?))
        {
            var application : CApplication? = (parent as CApplication?)
            if (application?.getRight() == expression)
            {
                var leftType : CExpression? = getTypeOf(application?.getLeft())
                while ((leftType is CImplicitArrowExpression?))
                {
                    leftType = ((leftType as CImplicitArrowExpression?))?.getRight()
                }
                if ((leftType is CArrowExpression?))
                {
                    return ((leftType as CArrowExpression?))?.getLeft()
                }

                return null
            }

        }

        return null
    }

    public open fun getTypeOf(elementAt : PsiElement?) : CExpression? {
        checkTypes()
        var expression : CExpression? = myExpressions?.get(elementAt)
        if (expression != null)
        {
            return expression?.getType()
        }

        return null
    }
    public open fun printDebug() : Unit {
        for (declaration : CDeclaration? in myDeclarations?.values())
        {
            System.out?.println(declaration?.toString())
        }
    }
}
