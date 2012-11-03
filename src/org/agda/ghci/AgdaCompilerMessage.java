package org.agda.ghci;

public class AgdaCompilerMessage extends AgdaExernalAnnotation {
    private int myStart;
    private int myEnd;
    private String myText;

    public AgdaCompilerMessage(int start, int end, String text) {
        myStart = start;
        myEnd = end;
        myText = text;
    }

    public int getStart() {
        return myStart;
    }

    public int getEnd() {
        return myEnd;
    }

    public String getText() {
        return myText;
    }

    @Override
    public String toString() {
        return "[" + myText + ", " + myStart + ", " + myEnd + "]";
    }
}
