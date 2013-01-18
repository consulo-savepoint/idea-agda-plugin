package org.jetbrains.agda.core

import java.util.ArrayList

/**
 * @author Evgeny.Kurbatsky
 */
public class CFunctionDeclaration(val name : String, aType : CExpression) : CDeclaration(aType) {
    private val myBodes : MutableList<CFunctionBody> = ArrayList<CFunctionBody>()


    public fun addBody(body : CFunctionBody)  {
        myBodes.add(body);
    }

    public fun getBodyes() : List<CFunctionBody> {
        return myBodes;
    }

    public fun toString() : String {
        val builder : StringBuilder = StringBuilder()
        builder.append(name).append(" : ").append(aType).append(" {\n")
        for (expression in myBodes)
        {
            builder.append("\t").append(expression.toString()).append("\n")
        }
        builder.append("}")
        return builder.toString()
    }
}
