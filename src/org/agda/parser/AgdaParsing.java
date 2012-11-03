package org.agda.parser;


import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespaceSkippedCallback;
import com.intellij.psi.tree.IElementType;

public class AgdaParsing {
    PsiBuilder myBuilder;
    Integer myIndent;

    public AgdaParsing(PsiBuilder builder) {
        this.myBuilder = builder;
        builder.setWhitespaceSkippedCallback(new WhitespaceSkippedCallback() {
            public void onSkip(IElementType type, int start, int end) {
                if (type == AgdaTokenTypes.LINE_END) {
                    myIndent = null;
                } else if (myIndent == null) {
                    myIndent = end - start;
                }
            }
        });
    }

    int getIndent() {
        if (myIndent == null) {
            myIndent = 0;
        }
        return myIndent;
    }


    void parseFile() {
        PsiBuilder.Marker mark = myBuilder.mark();
        while (!myBuilder.eof()) {
            parseDeclaration();
        }
        mark.done(AgdaASTType.MODULE_DECLARATION);
    }

    private void parseDeclaration() {
        String tokenText = myBuilder.getTokenText();
        /*
        if (tokenText.equals("data")) {
            parseData();
        } else if (myBuilder.getTokenText().equals("module")) {
            PsiBuilder.Marker mark = myBuilder.mark();
            myBuilder.advanceLexer();
            String nameToken = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            match("where");
            mark.done(AgdaASTType.MODULE_DECLARATION);
        } else {
            PsiBuilder.Marker mark = myBuilder.mark();
            myBuilder.getTokenText();
            myBuilder.advanceLexer();
            String text = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            mark.rollbackTo();
             */
            /*
            if (":".equals(text)) {
                parseFunctionDeclaration();
            }
            */
            IElementType token = myBuilder.getTokenType();

            if (token != null) {
                PsiBuilder.Marker marker = myBuilder.mark();
                myBuilder.advanceLexer();
                marker.done(AgdaASTType.NAMED_ELEMENT);
            } else {
                myBuilder.advanceLexer();
            }


        //}
    }

    private void parseFunctionDeclaration() {
        PsiBuilder.Marker mark = myBuilder.mark();
        String name = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        match(":");
        parseType();
    }

    private void match(String str) {
        String name = myBuilder.getTokenText();

        myBuilder.advanceLexer();
        if (!str.equals(name)) {
            myBuilder.error(str + " expected");
        }
    }

    private void parseData() {
        PsiBuilder.Marker mark = myBuilder.mark();
        match("data");
        int indent = getIndent();
        String name = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        match(":");
        parseType();

        match("where");

        parseConstructors(indent);
        mark.done(AgdaASTType.TYPE_REFERENCE);
    }

    private void parseConstructors(int parentIndent) {
        myBuilder.getTokenText();
        int indent = getIndent();
        if (indent <= parentIndent) {
            myBuilder.error("indent expected");
            return;
        }
        while (indent > parentIndent && !myBuilder.eof()) {
            //PsiBuilder.Marker mark = myBuilder.mark();
            String name = myBuilder.getTokenText();
            myBuilder.advanceLexer();

            match(":");

            parseType();
            //mark.done(AgdaASTType.CONSTRUCTOR_DECLARATION);
            myBuilder.getTokenText();
            indent = getIndent();

        }

    }

    private void parseType() {
        PsiBuilder.Marker mark = myBuilder.mark();
        String nameToken = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        mark.done(AgdaASTType.TYPE_REFERENCE);

        if ("->".equals(myBuilder.getTokenText())) {
            myBuilder.advanceLexer();
            PsiBuilder.Marker mark2 = myBuilder.mark();
            String nameToken2 = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            mark2.done(AgdaASTType.TYPE_REFERENCE);
        }
    }
}
