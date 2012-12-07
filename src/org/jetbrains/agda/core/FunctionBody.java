package org.jetbrains.agda.core;

/**
 * @author Evgeny.Kurbatsky
 */
public class FunctionBody {
    CExpression myLeft;
    CExpression myRight;

    public FunctionBody(CExpression left, CExpression right) {
        this.myLeft = left;
        this.myRight = right;
    }

    @Override
    public String toString() {
        return myLeft + " = " + myRight;
    }
}
