package org.jetbrains.agda.core;

/**
 * @author Evgeny.Kurbatsky
 */
public class ArrowExpression extends CExpression {
    private final CExpression myLeft;
    private final CExpression myFight;

    public ArrowExpression(CExpression left, CExpression right) {
        myLeft = left;
        myFight = right;
        attach(left);
        attach(right);
    }

    public CExpression getLeft() {
        return myLeft;
    }

    public CExpression getRight() {
        return myFight;
    }

    @Override
    public String toString() {
        return "(" + myLeft + " -> " + myFight + ')';
    }
}
