package org.jetbrains.agda.core

import java.util.ArrayList

/**
 * @author Evgeny.Kurbatsky
 */
public open class CDataDeclaration(name : String?, `type` : CExpression?) : CDeclaration() {
    private var myName : String? = null
    private var myConstructors : MutableList<CTypeSignature?> = ArrayList<CTypeSignature?>()
    public open fun getName() : String? {
        return myName
    }
    public open fun getConstructors() : MutableList<CTypeSignature?> {
        return myConstructors
    }
    public fun toString() : String? {
        var builder : StringBuilder? = StringBuilder()
        builder?.append("data ")?.append(myName)?.append(" : ")?.append(myType)?.append(" {\n")
        for (cConstructor : CTypeSignature? in myConstructors)
        {
            builder?.append("\t")?.append(cConstructor)?.append("\n")
        }
        builder?.append("}")
        return builder?.toString()
    }
    {
        myName = name
        myType = `type`
    }

}
