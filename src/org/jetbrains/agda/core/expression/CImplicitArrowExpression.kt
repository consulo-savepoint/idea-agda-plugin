package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public class CImplicitArrowExpression(val name : String, left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    public fun toString() : String? {
        return "{" + name + ":" + left + "}->" + right
    }
}
