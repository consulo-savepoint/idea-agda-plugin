package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CArrowExpression(left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    public fun toString() : String? {
        return "(" + getLeft() + " -> " + getRight() + ')'
    }


}
