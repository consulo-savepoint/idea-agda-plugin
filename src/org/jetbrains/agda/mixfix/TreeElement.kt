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
public abstract class TreeElement() {
    public open fun getText() : String? = null

    public fun isTerm() : Boolean =
        this is TermElement

    public open fun getDeclaration() : PsiElement? {
        return null
    }

    public abstract fun getDeclaration(element : PsiElement) : PsiElement?

}

public class TermElement(val element : PsiElement) : TreeElement() {

    public override fun getText() : String? {
        if (element is FqName) {
            return element.getText()
        }

        return null
    }


    public override fun getDeclaration(element : PsiElement) : PsiElement? {
        return null
    }

}

public class ParentElement(val myDeclaration : PsiElement?, val myChildren : List<TreeElement>) : TreeElement() {

    public override fun getDeclaration() : PsiElement? = myDeclaration

    public fun getChildren() : List<TreeElement> {
        return myChildren
    }

    public override fun getDeclaration(element : PsiElement) : PsiElement? {
        for (child : TreeElement? in myChildren) {
            when(child) {
                is TermElement -> if (child.element == element) return myDeclaration
                is ParentElement -> {
                    var declaration : PsiElement? = child.getDeclaration(element)
                    if (declaration != null) {
                        return declaration
                    }
                }
                else -> throw RuntimeException()
            }
        }
        return null
    }
}

