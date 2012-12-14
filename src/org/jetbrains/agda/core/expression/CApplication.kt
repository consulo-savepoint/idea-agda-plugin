package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CApplication(val left : CExpression, val right : CExpression) : CExpression() {
    fun toString() : String {
        return "(" + left + " " + right + ")"
    }
    {
        attach(left)
        attach(right)
    }

}
