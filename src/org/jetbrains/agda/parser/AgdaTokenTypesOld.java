package org.jetbrains.agda.parser;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;


public interface AgdaTokenTypesOld {
  TokenSet COMMENTS = TokenSet.create(AgdaTokenTypes.END_OF_LINE_COMMENT, AgdaTokenTypes.COMMENT);
  TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE, AgdaTokenTypes.NEW_LINE);


}