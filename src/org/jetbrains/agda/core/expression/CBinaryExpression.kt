package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public abstract class CBinaryExpression(val left : CExpression, val right : CExpression) : CExpression() {
    {
        attach(left)
        attach(right)
    }

}
