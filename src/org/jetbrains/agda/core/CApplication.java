package org.jetbrains.agda.core;

/**
 * @author Evgeny.Kurbatsky
 */
public class CApplication extends CExpression {
    CExpression myLeft;
    CExpression myRight;

    public CApplication(CExpression left, CExpression right) {
        this.myLeft = left;
        this.myRight = right;
    }

    @Override
    public String toString() {
        return "(" + myLeft + " " + myRight + ")";
    }
}
