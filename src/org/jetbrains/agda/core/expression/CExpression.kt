package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CExpression() {
    private var myParent : CExpression? = null
    private var myType : CExpression? = null
    public open fun getParent() : CExpression? {
        return myParent
    }
    public open fun getType() : CExpression? {
        return myType
    }
    protected open fun attach(expression : CExpression) : Unit {
        expression.myParent = this
    }
    public open fun setType(aType : CExpression?) : Unit {
        this.myType = aType
    }


}
