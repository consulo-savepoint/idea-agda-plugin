package org.jetbrains.agda.lisp;

public class LispParser {
    private String myText;
    private int myIndex = 0;
    private int myTokenStart;

    public LispParser(String text) {
        myText = text;
    }


    private char ch() {
        if (myIndex < myText.length()) {
            return myText.charAt(myIndex);
        } else {
            return '\0';
        }
    }

    public SExpression parseExpression() {
        skipWhitespace();
        if (ch() == '\'') {
            myIndex++;
        }
        if (ch() == '(') {
            myIndex++;
            SExpression result = new SExpression();
            while (ch() != ')') {
                result.add(parseExpression());
                skipWhitespace();
            }
            myIndex++;
            return result;
        } else if (ch() == '"') {
            myIndex++;
            startToken();
            while (ch() != '"') {
                if (ch() == '\\') {
                    myIndex++;
                }
                myIndex++;
            }
            SExpression expression = new SExpression(getToken().replace("\\\"", "\""));
            myIndex++;
            return expression;
        } else {
            startToken();
            while (!Character.isWhitespace(ch()) && ch() != 0 && ch() != ')') {
                myIndex++;
            }
            return new SExpression(getToken());
        }
    }

    private void startToken() {
        myTokenStart = myIndex;
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch())) {
            myIndex++;
        }
    }

    public String getToken() {
        return new String(myText.substring(myTokenStart, myIndex));
    }
}
