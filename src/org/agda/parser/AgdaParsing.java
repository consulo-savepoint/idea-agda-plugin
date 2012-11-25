package org.agda.parser;


import com.intellij.lang.PsiBuilder;
import com.intellij.lang.WhitespaceSkippedCallback;
import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AgdaParsing implements AgdaASTTypes{
    static final String[][] BRACES = new String[][] {
            {"(",")"},
            {"{","}"}
    };
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
            parseBindings();
            match("where");
            mark.done(AgdaASTTypes.MODULE_DECLARATION);
        } if ("open".equals(myBuilder.getTokenText())) {
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
        if (tryMatch("as")) {
            parseQName();
        }
        if (tryMatch("hiding")) {
            match("(");
            parseImportNames();
            match(")");
        }
        if (tryMatch("using")) {
            match("(");
            parseImportNames();
            match(")");
        }
        if (tryMatch("renaming")) {
            match("(");
            parseRenamingsNames();
            match(")");
        }
        mark.done(IMPORT);
    }

    private void parseRenamingsNames() {
        do {
            PsiBuilder.Marker mark = myBuilder.mark();
            getToken();
            match("to");
            getToken();
            mark.done(RENAMING);
        } while (tryMatch(";"));
    }

    private void parseImportNames() {
        PsiBuilder.Marker mark = myBuilder.mark();
        do {
            tryMatch("module");
            getToken();
        } while (tryMatch(";"));
        mark.done(NAME);
    }

    private void parseFunctionDeclaration() {
        PsiBuilder.Marker mark = myBuilder.mark();
        String name = myBuilder.getTokenText();
        myBuilder.advanceLexer();
        match(":");
        parseExpression();
    }

    private boolean match(String str) {
        String name = myBuilder.getTokenText();

        myBuilder.advanceLexer();
        if (!str.equals(name)) {
            myBuilder.error(str + " expected");
            return false;
        }
        return true;
    }

    private void parseData() {
        PsiBuilder.Marker mark = myBuilder.mark();
        match("data");
        int indent = getIndent();

        String name = myBuilder.getTokenText();
        myBuilder.advanceLexer();

        parseBindings();
        if (! match(":")) {
            mark.drop();
            return;
        }
        parseExpression();

        match("where");

        parseConstructors(indent);

        mark.done(TYPE_DECLARATION);
    }

    private void parseBindings() {
        String[] brace = getBrace(lookAtToken());
        if (brace != null) {
            PsiBuilder.Marker mark = myBuilder.mark();

            match(brace[0]);
            do {
                getToken();
                if (tryMatch(":")) {
                    parseExpression();
                    if (!match(brace[1])) {
                        mark.drop();
                        return;
                    }
                    break;
                }
            } while (!tryMatch(brace[1]));


            mark.done(AgdaASTTypes.BINDINGS);

            parseBindings();
        }
    }

    private String lookAtToken() {
        return myBuilder.getTokenText();
    }

    private String[] getBrace(String str) {
        for (int i = 0; i < BRACES.length; i++) {
            if (BRACES[i][0].equals(str)) {
                return BRACES[i];
            }
        }
        return null;
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

            parseExpression();
            //mark.done(AgdaASTTypes.CONSTRUCTOR_DECLARATION);
            myBuilder.getTokenText();
            indent = getIndent();

        }

    }

    private void parseExpression() {
        parseApplication();

        if ("→".equals(myBuilder.getTokenText()) || "->".equals(myBuilder.getTokenText())) {
            myBuilder.advanceLexer();
            parseApplication();
        }
    }

    private void parseApplication() {
        PsiBuilder.Marker last = null;
        do {
            PsiBuilder.Marker mark = myBuilder.mark();
            String nameToken = myBuilder.getTokenText();
            myBuilder.advanceLexer();
            mark.done(AgdaASTTypes.REFERENCE);
            if (myBuilder.getTokenType() == AgdaTokenTypes.NAME) {
                if (last != null) {
                    last.done(AgdaASTTypes.APPLICATION);
                    last = last.precede();
                } else {
                    last = mark.precede();
                }
            } else {
                break;
            }
        } while (true);
        if (last != null) {
            last.done(AgdaASTTypes.APPLICATION);
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
