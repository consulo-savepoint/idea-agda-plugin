package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CTypeSignature(name : String, aType : CExpression) : CDeclaration(aType) {
    private var myName : String? = null
    public open fun getName() : String? {
        return myName
    }
    public fun toString() : String? {
        return myName + " : " + aType
    }
    {
        this.myName = name
    }

}
