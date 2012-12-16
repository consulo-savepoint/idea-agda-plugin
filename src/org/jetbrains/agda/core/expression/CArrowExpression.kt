package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public class CArrowExpression(left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    public fun toString() : String {
        return "(" + left + " -> " + right + ")"
    }


}
