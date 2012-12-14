package org.jetbrains.agda.core

import java.util.ArrayList

/**
 * @author Evgeny.Kurbatsky
 */
public open class CFunctionDeclaration(name : String?, `type` : CExpression?) : CDeclaration() {
    private var myName : String? = null
    private val myBodes : MutableList<FunctionBody?> = ArrayList<FunctionBody?>()
    public open fun getName() : String? {
        return myName
    }
    public open fun getBodyes() : MutableList<FunctionBody?>? {
        return myBodes;
    }
    public fun toString() : String? {
        var builder : StringBuilder? = StringBuilder()
        builder?.append(myName)?.append(" : ")?.append(myType)?.append(" {\n")
        for (expression : FunctionBody? in myBodes)
        {
            builder?.append("\t")?.append(expression?.toString())?.append("\n")
        }
        builder?.append("}")
        return builder?.toString()
    }
    {
        this.myName = name
        this.myType = `type`
    }

}
