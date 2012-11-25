package org.agda.parser;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;


public interface AgdaTokenTypes {
  IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
  IElementType LINE_END = new AgdaToken("NEW_LINE");
  IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

  IElementType COMMENT = new AgdaToken("COMMENT");
  IElementType END_OF_LINE_COMMENT = new AgdaToken("END_OF_LINE_COMMENT");
  IElementType NAME = new AgdaToken("IMPORT_NAME");
  IElementType SPEC_CHARACTERS = new AgdaToken("SPEC_CHARACTERS");
  IElementType KEYWORD = new AgdaToken("KEYWORD");

  IElementType STRING_LITERAL = new AgdaToken("STRING_LITERAL");

  IElementType L_PAREN = new AgdaToken("L_PAREN");
  IElementType R_PAREN = new AgdaToken("R_PAREN");

  IElementType L_CURLY = new AgdaToken("L_CURLY");
  IElementType R_CURLY = new AgdaToken("R_CURLY");


  TokenSet COMMENTS = TokenSet.create(END_OF_LINE_COMMENT, COMMENT);
  TokenSet WHITESPACES = TokenSet.create(WHITE_SPACE, LINE_END);


}