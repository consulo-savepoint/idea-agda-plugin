package org.agda.ghci;

/**
 * @author Evgeny.Kurbatsky
 */
public class GoalAnnotation extends AgdaExternalAnnotation {

    private int myIndex;
    private String myText;

    public GoalAnnotation(int index, String text) {
        myIndex = index;
        myText = text;
    }

    public int getIndex() {
        return myIndex;
    }

    public String getText() {
        return myText;
    }
}
