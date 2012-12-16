package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class FunctionBody(val left : CExpression, val right : CExpression) {
    public open fun toString() : String? {
        return left.toString() + " = " + right.toString()
    }

}
