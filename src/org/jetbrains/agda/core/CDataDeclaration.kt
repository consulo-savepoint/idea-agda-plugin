package org.jetbrains.agda.core

import java.util.ArrayList

/**
 * @author Evgeny.Kurbatsky
 */
public class CDataDeclaration(val name : String, aType : CExpression) : CDeclaration(aType) {
    private var myConstructors : MutableList<CTypeSignature> = ArrayList<CTypeSignature>()

    public fun getConstructors() : MutableList<CTypeSignature> {
        return myConstructors
    }
    public fun toString() : String? {
        val builder = StringBuilder()
        builder.append("data ").append(name).append(" : ").append(aType).append(" {\n")
        for (cConstructor : CTypeSignature? in myConstructors)
        {
            builder.append("\t").append(cConstructor).append("\n")
        }
        builder.append("}")
        return builder.toString()
    }
}
