package org.jetbrains.agda.core

import com.intellij.psi.PsiElement

/**
 * @author Evgeny.Kurbatsky
 */
public open class CRefExpression(declaration : PsiElement?, text : String?) : CExpression() {
    private var myDeclaration : PsiElement? = null
    private var myText : String? = null
    public open fun getDeclaration() : PsiElement? {
        return myDeclaration
    }
    public open fun getText() : String? {
        return myText
    }
    public fun toString() : String? {
        return myText
    }
    {
        myDeclaration = declaration
        myText = text
    }

}
