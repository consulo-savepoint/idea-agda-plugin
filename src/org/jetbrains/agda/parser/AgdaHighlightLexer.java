package org.jetbrains.agda.parser;

import com.google.common.collect.Lists;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;

import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AgdaHighlightLexer extends FlexAdapter {
    public AgdaHighlightLexer() {
        super(new _AgdaLexer((Reader) null));
    }
}