package org.jetbrains.agda.parser;

import java.util.*;
import com.intellij.lexer.*;
import com.intellij.psi.*;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;
import com.intellij.psi.tree.IElementType;

%%

%unicode
%class _AgdaLexer
%implements FlexLexer

%{
    private int prevIndentSize;
%}


%function advance
%type IElementType
%eof{
  return;
%eof}

%xstate STRING, BLOCK_COMMENT

DIGIT=[0-9]
WHITE_SPACE_CHAR=[\ \t\f]
INDENT=[\n] {WHITE_SPACE_CHAR}* [^\ \t\f]
EOL_COMMENT="--"[^\n]*
LETTER = [^0-9{}().\ \n\t\f:]
IDENTIFIER_PART=[:digit:]|{LETTER}
IDENTIFIER={LETTER} {IDENTIFIER_PART}*

%%


{INDENT} { yybegin(YYINITIAL);
        int indentSize = yytext().length() - 2;
        yypushback(1);
        IElementType result;
        if (indentSize > prevIndentSize) {
          result = AgdaTokenTypes.VIRTUAL_LEFT_PAREN;
        } else if (indentSize < prevIndentSize) {
          result = AgdaTokenTypes.VIRTUAL_RIGHT_PAREN;
        } else {
          result = AgdaTokenTypes.VIRTUAL_SEMICOLON;
        }
        prevIndentSize = indentSize;
        return result;
      }

({WHITE_SPACE_CHAR})+ { return TokenType.WHITE_SPACE; }
{EOL_COMMENT} { return AgdaTokenTypes.END_OF_LINE_COMMENT; }
"{"          { return AgdaTokenTypes.LEFT_BRACE; }
"}"          { return AgdaTokenTypes.RIGHT_BRACE; }
"("          { return AgdaTokenTypes.LEFT_PAREN; }
")"          { return AgdaTokenTypes.RIGHT_PAREN; }
":"          { return AgdaTokenTypes.COLON;}
";"          { return AgdaTokenTypes.SEMICOLON;}
"."          { return AgdaTokenTypes.DOT; }
"="          { return AgdaTokenTypes.ASSIGNMENT; }
"data"       { return AgdaTokenTypes.DATA_KEYWORD; }
"where"      { return AgdaTokenTypes.WHERE_KEYWORD; }

{IDENTIFIER} { return AgdaTokenTypes.ID; }
"\n"               { return AgdaTokenTypes.VIRTUAL_RIGHT_PAREN; }
. { return TokenType.BAD_CHARACTER; }