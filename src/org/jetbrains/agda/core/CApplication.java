package org.jetbrains.agda.core;

/**
 * @author Evgeny.Kurbatsky
 */
public class CApplication extends CExpression {
    private final CExpression myLeft;
    private final CExpression myRight;

    public CApplication(CExpression left, CExpression right) {
        this.myLeft = left;
        this.myRight = right;
        attach(left);
        attach(right);
    }

    public CExpression getLeft() {
        return myLeft;
    }

    public CExpression getRight() {
        return myRight;
    }

    @Override
    public String toString() {
        return "(" + myLeft + " " + myRight + ")";
    }
}
