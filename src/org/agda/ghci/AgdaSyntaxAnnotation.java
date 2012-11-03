package org.agda.ghci;


import org.agda.lisp.SExpression;

import java.util.ArrayList;
import java.util.List;

public class AgdaSyntaxAnnotation extends AgdaExernalAnnotation {
    private int myStart;
    private int myEnd;
    private String myType;
    private String myReference;
    private int myReferenceIndex;

    public AgdaSyntaxAnnotation(int start, int end, String type, String referenceFile, int referenceIndex) {
        myStart = start;
        myEnd = end;
        myType = type;
        myReference = referenceFile;
        myReferenceIndex = referenceIndex;
    }

    public static List<AgdaSyntaxAnnotation> parse(SExpression expr) {
        List<AgdaSyntaxAnnotation> result = new ArrayList<AgdaSyntaxAnnotation>();
        for (SExpression expression: expr.getChildren()) {
            int start = Integer.parseInt(expression.get(0).getValue()) - 1;
            int end = Integer.parseInt(expression.get(1).getValue()) - 1;
            String type = expression.get(2).get(0).getValue();
            String referenceFile = null;
            int referenceIndex = 0;
            if (expression.getChildren().size() == 5) {
                SExpression ref = expression.get(4);
                referenceFile = ref.get(0).getValue();
                referenceIndex = Integer.parseInt(ref.get(2).getValue()) - 1;
            }
            result.add(new AgdaSyntaxAnnotation(start, end, type, referenceFile, referenceIndex));
        }
        return result;
    }

    public String getType() {
        return myType;
    }

    public int getStart() {
        return myStart;
    }

    public int getEnd() {
        return myEnd;
    }

    @Override
    public String toString() {
        return "(" + myStart + ":" + myEnd + " - " + myType + ")";
    }

    public String getReference() {
        return myReference;
    }

    public int getReferenceIndex() {
        return myReferenceIndex;
    }
}
