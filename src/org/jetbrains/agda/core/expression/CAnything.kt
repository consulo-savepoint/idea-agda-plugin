package org.jetbrains.agda.core.expression

import org.jetbrains.agda.core.CExpression

/**
 * @author Evgeny.Kurbatsky
 */
public class CAnything: CExpression() {
    public fun toString() : String? {
        return "_"
    }
}