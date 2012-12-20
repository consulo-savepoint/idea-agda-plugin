package org.jetbrains.agda.core

import com.intellij.psi.PsiElement

/**
 * @author Evgeny.Kurbatsky
 */
public class CRefExpression(val declaration : Any, val  name: String) : CExpression() {

    fun equals(other : Any) : Boolean {
        if (other is CRefExpression) {
            return other.declaration.equals(declaration);
        }
        return false;
    }

    fun toString() : String {
        return name
    }



}
