package org.agda.parser;

import com.google.common.collect.Lists;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AgdaLexer extends LexerBase {
    private static Set<Character> SPEC_CHARACTERS = new HashSet<Character>(Lists.charactersOf(";.’”(){}@"));
    private static Set<String> KEYWORDS = new HashSet<String>(Arrays.asList(new String[]{
            "forall",     "private",     "hiding",
            "public",     "import",      "quoteGoal",
            "in",         "quoteTerm",   "infix",
            "quote",      "infixl",      "record",
            "infixr",     "renaming",    "let",
            "rewrite",    "module",      "abstract",
            "mutual",     "syntax",      "codata",
            "open",       "unquote",     "constructor",
            "postulate",  "using",       "data",
            "primitive",  "where",       "field",
            "Prop",       "with",        "∀"}));

    private CharSequence myBuffer;
    private int myBufferIndex;
    private int myBufferEndOffset;
    private int myTokenEndOffset;  // positioned after the last symbol of the current token
    private IElementType myTokenType;

    @Override
    public final void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
        myBuffer = buffer;
        myBufferIndex = startOffset;
        myBufferEndOffset = endOffset;
        myTokenType = null;
        myTokenEndOffset = startOffset;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public final IElementType getTokenType() {
        if (myTokenType == null) _locateToken();

        return myTokenType;
    }

    private void _locateToken() {
        if (myTokenEndOffset == myBufferEndOffset) {
            myTokenType = null;
            myBufferIndex = myBufferEndOffset;
            return;
        }

        myBufferIndex = myTokenEndOffset;
        char ch = myBuffer.charAt(myBufferIndex);
        if (Character.isWhitespace(ch)) {
            if (ch == '\n') {
                myTokenEndOffset++;
                myTokenType = AgdaTokenTypes.LINE_END;
                return;
            }
            while (Character.isWhitespace(ch) && ch != '\n') {
                myTokenEndOffset++;
                if (myTokenEndOffset == myBufferEndOffset) {
                    break;
                }
                ch = myBuffer.charAt(myTokenEndOffset);

            }
            myTokenType = AgdaTokenTypes.WHITE_SPACE;
            return;
        }

        if (SPEC_CHARACTERS.contains(ch)) {
            switch (ch) {
                case '(':
                    myTokenType = AgdaTokenTypes.L_PAREN;
                    break;
                case ')':
                    myTokenType = AgdaTokenTypes.R_PAREN;
                    break;
                case '{':
                    if (charAt(myBufferIndex + 1) == '-') {
                        parseComment();
                        return;
                    }
                    myTokenType = AgdaTokenTypes.L_CURLY;
                    break;
                case '}':
                    myTokenType = AgdaTokenTypes.R_CURLY;
                    break;
                default:
                    myTokenType = AgdaTokenTypes.SPEC_CHARACTERS;
            }
            myTokenEndOffset++;
            return;
        }

        while (!Character.isWhitespace(ch) && !SPEC_CHARACTERS.contains(ch)) {
            myTokenEndOffset++;
            CharSequence charSequence = myBuffer.subSequence(myBufferIndex, myTokenEndOffset);
            if (charSequence.length() == 2 && charSequence.toString().equals("--")) {
                while (myTokenEndOffset < myBufferEndOffset && myBuffer.charAt(myTokenEndOffset) != '\n') {
                    myTokenEndOffset++;
                }
                myTokenType = AgdaTokenTypes.END_OF_LINE_COMMENT;
                return;
            }
            if (myTokenEndOffset >= myBufferEndOffset) {
                break;
            }
            ch = myBuffer.charAt(myTokenEndOffset);
        }
        CharSequence charSequence = myBuffer.subSequence(myBufferIndex, myTokenEndOffset);
        if (KEYWORDS.contains(charSequence.toString())) {
            myTokenType = AgdaTokenTypes.KEYWORD;
        } else {
            myTokenType = AgdaTokenTypes.VALUE_CHARACTERS;
        }
    }

    private void parseComment() {
        while (myTokenEndOffset < myBufferEndOffset) {
            if (charAt(myTokenEndOffset - 2) == '-' && charAt(myTokenEndOffset - 1) == '}') {
                break;
            }
            myTokenEndOffset++;
        }
        myTokenType = AgdaTokenTypes.COMMENT;
    }

    private char charAt(int i) {
        if (i < myBuffer.length())  {
            return myBuffer.charAt(i);
        } else {
            return 0;
        }
    }

    @Override
    public final int getTokenStart() {
        return myBufferIndex;
    }

    @Override
    public final int getTokenEnd() {
        if (myTokenType == null) _locateToken();
        return myTokenEndOffset;
    }


    @Override
    public final void advance() {
        if (myTokenType == null) _locateToken();
        myTokenType = null;
    }

    @Override
    public CharSequence getBufferSequence() {
        return myBuffer;
    }

    @Override
    public final int getBufferEnd() {
        return myBufferEndOffset;
    }
}
