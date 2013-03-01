package org.jetbrains.agda.core

import java.util.ArrayList

/**
 * @author Evgeny.Kurbatsky
 */
public class CFunctionDeclaration(val name : String, aType : CExpression, val bodies : List<CFunctionBody>) : CDeclaration(aType) {

    public fun toString() : String {
        val builder : StringBuilder = StringBuilder()
        builder.append(name).append(" : ").append(aType).append(" {\n")
        for (expression in bodies)
        {
            builder.append("\t").append(expression.toString()).append("\n")
        }
        builder.append("}")
        return builder.toString()
    }
}
