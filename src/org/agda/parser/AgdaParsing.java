package org.agda.parser;


import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespaceSkippedCallback;
import com.intellij.psi.tree.IElementType;

public class AgdaParsing implements AgdaASTTypes{
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
        mark.done(AgdaASTTypes.MODULE_DECLARATION);
    }

    private void parseDeclaration() {
        String tokenText = myBuilder.getTokenText();

        if (tokenText.equals("data")) {
            parseData();
        } else if (myBuilder.getTokenText().equals("module")) {
            PsiBuilder.Marker mark = myBuilder.mark();
            myBuilder.advanceLexer();
            parseQName();
            match("where");
            mark.done(AgdaASTTypes.MODULE_DECLARATION);
        } if (myBuilder.getTokenText().equals("open")) {
            PsiBuilder.Marker mark = myBuilder.mark();
            myBuilder.advanceLexer();
            if (tryMatch("import")) {
                parseImport(mark);
            } else {
                mark.drop();
            }
        } else {
            /*
            PsiBuilder.Marker mark = myBuilder.mark();
            myBuilder.getTokenText();
            myBuilder.advanceLexer();
            String text = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            mark.rollbackTo();

            if (":".equals(text)) {
                parseFunctionDeclaration();
            }
            */
            IElementType token = myBuilder.getTokenType();

            if (token != null) {
                PsiBuilder.Marker marker = myBuilder.mark();
                myBuilder.advanceLexer();
                marker.done(AgdaASTTypes.NAMED_ELEMENT);
            } else {
                myBuilder.advanceLexer();
            }


        }
    }

    private void parseImport(PsiBuilder.Marker mark) {
        parseQName();
        if (tryMatch("hiding")) {
            match("(");
            parseImportNames();
            match(")");
        }
        mark.done(IMPORT);
    }

    private void parseImportNames() {
        PsiBuilder.Marker mark = myBuilder.mark();
        getToken();
        mark.done(NAME);
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
        mark.done(AgdaASTTypes.TYPE_REFERENCE);
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
            //mark.done(AgdaASTTypes.CONSTRUCTOR_DECLARATION);
            myBuilder.getTokenText();
            indent = getIndent();

        }

    }

    private void parseType() {
        PsiBuilder.Marker mark = myBuilder.mark();
        String nameToken = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        mark.done(AgdaASTTypes.TYPE_REFERENCE);

        if ("->".equals(myBuilder.getTokenText())) {
            myBuilder.advanceLexer();
            PsiBuilder.Marker mark2 = myBuilder.mark();
            String nameToken2 = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            mark2.done(TYPE_REFERENCE);
        }
    }

    public  void parseQName() {
        PsiBuilder.Marker marker = myBuilder.mark();
        do {
            getToken();
        } while (tryMatch("."));
        marker.done(QNAME);
    }

    private boolean tryMatch(String text) {
        String name = myBuilder.getTokenText();

        if (text.equals(name)) {
            myBuilder.advanceLexer();
            return true;
        }
        return false;
    }

    public String getToken() {
        String text = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        return text;
    }
}
