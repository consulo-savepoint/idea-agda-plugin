package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public class CSet(val level : Int) : CExpression() {
    public fun toString() : String {
        return "Set " + level
    }


}
