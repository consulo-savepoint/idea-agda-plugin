package org.jetbrains.agda.core

import org.jetbrains.agda.psi.FqName

/**
 * @author Evgeny.Kurbatsky
 */
public class CFunctionBody(val left : CExpression, val right : CExpression) {
    public fun toString() : String? {
        return left.toString() + " = " + right.toString()
    }

    public fun getDeclaration() : Any =
            getDeclaration(left)

    public fun getDeclaration(expression : CExpression) : Any =
        when(expression) {
            is CRefExpression -> expression.declaration;
            is CApplication -> getDeclaration(expression.left)
            else -> throw RuntimeException()
        }

}
