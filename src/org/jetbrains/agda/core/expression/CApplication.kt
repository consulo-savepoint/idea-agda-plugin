package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public class CApplication(val left : CExpression, val right : CExpression) : CExpression() {
    {
        attach(left)
        attach(right)
    }
    fun toString() : String {
        return "(" + left + " " + right + ")"
    }
    fun equals(other : Any) : Boolean {
        if (other is CApplication) {
            return other.left == left && other.right == right;
        }
        return false;
    }
}
