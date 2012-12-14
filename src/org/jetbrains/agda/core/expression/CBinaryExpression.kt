package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CBinaryExpression(left : CExpression, right : CExpression) : CExpression() {
    private val myLeft : CExpression?
    private val myRight : CExpression?

    public open fun getLeft() : CExpression? {
        return myLeft
    }
    public open fun getRight() : CExpression? {
        return myRight
    }
    {
        this.myLeft = left
        this.myRight = right
        attach(left)
        attach(right)
    }

}
