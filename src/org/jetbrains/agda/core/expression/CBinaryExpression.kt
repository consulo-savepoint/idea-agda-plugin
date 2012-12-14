package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CBinaryExpression(val left : CExpression, val right : CExpression) : CExpression() {
    {
        attach(left)
        attach(right)
    }

}
