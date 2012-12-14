package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CDeclaration() {
    protected var myType : CExpression? = null
    public open fun getType() : CExpression? {
        return myType
    }


}
