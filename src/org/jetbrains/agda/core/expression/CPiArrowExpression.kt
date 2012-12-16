package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public class CPiArrowExpression(val name : String, left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    public fun getText() : String {
        return name
    }

    public fun toString() : String? {
        return "(" + name + ":" + left + ") -> " + right
    }

}
