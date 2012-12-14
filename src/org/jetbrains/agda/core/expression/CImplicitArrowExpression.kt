package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
public open class CImplicitArrowExpression(text : String, left : CExpression, right : CExpression) : CBinaryExpression(left, right) {
    private val myText : String?
    public open fun getName() : String? {
        return myText
    }
    public fun toString() : String? {
        return "{" + myText + ":" + getLeft() + "}->" + getRight()
    }
    {
        myText = text
    }

}
