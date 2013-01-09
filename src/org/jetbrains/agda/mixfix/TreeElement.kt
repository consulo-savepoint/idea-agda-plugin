package org.jetbrains.agda.mixfix

import com.intellij.psi.PsiElement
import org.jetbrains.agda.psi.FqName

/**
 * Created with IntelliJ IDEA.
 * User: Atsky
 * Date: 09.01.13
 * Time: 2:33
 * To change this template use File | Settings | File Templates.
 */
public open class TreeElement(element : PsiElement?, declaration : PsiElement?, children : List<TreeElement>) {
    private val myChildren : List<TreeElement>
    private val myElement : PsiElement?
    private val myDeclaration : PsiElement?
    public open fun getText() : String? {
        if ((myElement is FqName?))
        {
            return myElement?.getText()
        }

        return null
    }
    
    public open fun getChildren() : List<TreeElement> {
        return myChildren
    }
    
    public open fun getElement() : PsiElement? {
        return myElement
    }
    public open fun isTerm() : Boolean {
        return (myElement is FqName?)
    }
    public open fun getDeclaration() : PsiElement? {
        return myDeclaration
    }
    public open fun getDeclaration(element : PsiElement?) : PsiElement? {
        for (child : TreeElement? in myChildren)
        {
            if (child?.getElement() == element)
            {
                return myDeclaration
            }

            var declaration : PsiElement? = child?.getDeclaration(element)
            if (declaration != null)
            {
                return declaration
            }

        }
        return null
    }
    {
        myElement = element
        myDeclaration = declaration
        myChildren = children
    }

}
