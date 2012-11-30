package org.jetbrains.agda.lisp;

import java.util.ArrayList;
import java.util.List;

public class SExpression {
    private boolean isAtom;
    private List<SExpression> myChildren;
    private String myValue;

    public SExpression() {
        isAtom = false;
        this.myChildren = new ArrayList<SExpression>();
    }

    public SExpression(String value) {
        isAtom = true;
        myValue = value;
    }

    public void add(SExpression expression) {
        myChildren.add(expression);
    }

    public List<SExpression> getChildren() {
        return myChildren;
    }

    public String getValue() {
        return myValue;
    }

    @Override
    public String toString() {
        if (isAtom) {
            return myValue;
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("(");
            for (SExpression e: myChildren) {
                buffer.append(e).append(" ");
            }
            buffer.append(")");
            return buffer.toString();
        }
    }

    public SExpression get(int i) {
        if (myChildren == null || i >= myChildren.size()) {
            return null;
        } else {
            return myChildren.get(i);
        }
    }

    public boolean isAtom() {
        return isAtom;
    }

    public String getValue(int i) {
        return get(i).getValue();
    }
}
