package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CTypeSignature(name : String?, `type` : CExpression?) : CDeclaration() {
    private var myName : String? = null
    public open fun getName() : String? {
        return myName
    }
    public fun toString() : String? {
        return myName + " : " + myType
    }
    {
        this.myName = name
        this.myType = `type`
    }

}
