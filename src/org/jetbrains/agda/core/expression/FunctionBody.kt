package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class FunctionBody(left : CExpression?, right : CExpression?) {
    var myLeft : CExpression? = null
    var myRight : CExpression? = null
    public open fun toString() : String? {
        return myLeft.toString() + " = " + myRight.toString()
    }
    {
        this.myLeft = left
        this.myRight = right
    }

}
