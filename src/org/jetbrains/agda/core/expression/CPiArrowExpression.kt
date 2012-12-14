package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CPiArrowExpression(text : String, left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    private val myText : String?
    public open fun getText() : String? {
        return myText
    }
    public fun toString() : String? {
        return "(" + myText + ":" + left + ") -> " + right
    }
    {
        myText = text
    }

}
