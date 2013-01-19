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

    public abstract fun getDeclaration(element : PsiElement) : PsiElement?

}

public class TermElement(val element : PsiElement) : TreeElement() {

    public override fun getDeclaration(element: PsiElement): PsiElement? {
        return null;
    }

    public override fun getText() : String? {
        if (element is FqName) {
            return element.getText()
        }

        return null
    }

}

public class ApplicationTreeElement(val left : TreeElement, val right : TreeElement) : TreeElement() {
    public override fun getDeclaration(element: PsiElement): PsiElement? {
        if (left.getDeclaration(element) != null) {
            return left.getDeclaration(element)
        }
        if (right.getDeclaration(element) != null) {
            return right.getDeclaration(element)
        }
        return null;
    }
}

public class OperationElement(val declaration: PsiElement, val children: List<TreeElement>) : TreeElement() {

    public override fun getDeclaration(element : PsiElement) : PsiElement? {
        for (child : TreeElement? in children) {
            when(child) {
                is TermElement -> if (child.element == element) return declaration
                is OperationElement -> {
                    var declaration : PsiElement? = child.getDeclaration(element)
                    if (declaration != null) {
                        return declaration
                    }
                }
                is ApplicationTreeElement -> {
                    val element1 = child.getDeclaration(element)
                    if (element1 != null) {
                        return element1;
                    }
                }
                else -> throw RuntimeException()
            }
        }
        return null
    }
}

