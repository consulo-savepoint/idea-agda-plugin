package org.jetbrains.agda.core;

public class CExpression {
    private CExpression myParent = null;

    public CExpression() {

    }

    public CExpression getParent() {
        return myParent;
    }

    protected void attach(CExpression expression) {
        expression.myParent = this;
    }
}
