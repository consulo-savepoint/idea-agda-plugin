package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public abstract class CExpression() {
    private var myParent : CExpression? = null
    private var myType : CExpression? = null

    public fun getParent() : CExpression? {
        return myParent
    }
    public fun getType() : CExpression? {
        return myType
    }
    protected fun attach(expression : CExpression) : Unit {
        expression.myParent = this
    }
    public fun setType(aType : CExpression?) : Unit {
        this.myType = aType
    }

}
