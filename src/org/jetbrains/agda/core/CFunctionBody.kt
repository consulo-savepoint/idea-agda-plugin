package org.jetbrains.agda.core

import org.jetbrains.agda.psi.FqName
import org.jetbrains.agda.util.FList

/**
 * @author Evgeny.Kurbatsky
 */
public class CFunctionBody(val left : CExpression, val right : CExpression) {
    public fun toString() : String {
        return left.toString() + " = " + right.toString()
    }
}
