package org.jetbrains.agda.highlight;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.options.OptionsBundle;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.psi.tree.IElementType;
import gnu.trove.THashMap;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;
import org.jetbrains.agda.parser.AgdaLexer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class AgdaHighlighter extends SyntaxHighlighterBase {
  private static final Map<IElementType, TextAttributesKey> keys1;

  @NotNull
  public Lexer getHighlightingLexer() {
    return new AgdaLexer();
  }


  public static final TextAttributesKey STRING_LITERAL = TextAttributesKey.createTextAttributesKey(
    "STRING_LITERAL",
    SyntaxHighlighterColors.STRING.getDefaultAttributes()
  );

  public static final TextAttributesKey KEYWORD_VALUE = TextAttributesKey.createTextAttributesKey(
    "KEYWORD.VALUE",
    SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
  );

  public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
    "COMMENT",
    SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
  );

  public static final TextAttributesKey TYPE = TextAttributesKey.createTextAttributesKey(
    "TYPE",
    new TextAttributes(new Color(30, 150, 0), null, null, null, Font.BOLD)
  );

  public static final TextAttributesKey CONSTRUCTOR = TextAttributesKey.createTextAttributesKey(
     "CONSTRUCTOR",
     new TextAttributes(new Color(0, 110, 110), null, null, null, Font.ITALIC)
    );

  public static final TextAttributesKey PROPERTIES_VALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
    "PROPERTIES.VALID_STRING_ESCAPE",
    SyntaxHighlighterColors.VALID_STRING_ESCAPE.getDefaultAttributes()
  );
  public static final TextAttributesKey PROPERTIES_INVALID_STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
    "PROPERTIES.INVALID_STRING_ESCAPE",
    SyntaxHighlighterColors.INVALID_STRING_ESCAPE.getDefaultAttributes()
  );

  static {
    keys1 = new THashMap<IElementType, TextAttributesKey>();

    keys1.put(AgdaTokenTypes.END_OF_LINE_COMMENT, COMMENT);
    keys1.put(AgdaTokenTypes.COMMENT, COMMENT);
    keys1.put(AgdaTokenTypes.KEYWORD, KEYWORD_VALUE);
    keys1.put(AgdaTokenTypes.DATA_KEYWORD, KEYWORD_VALUE);
    keys1.put(AgdaTokenTypes.STRING, STRING_LITERAL);

    keys1.put(StringEscapesTokenTypes.VALID_STRING_ESCAPE_TOKEN, PROPERTIES_VALID_STRING_ESCAPE);
    keys1.put(StringEscapesTokenTypes.INVALID_CHARACTER_ESCAPE_TOKEN, PROPERTIES_INVALID_STRING_ESCAPE);
    keys1.put(StringEscapesTokenTypes.INVALID_UNICODE_ESCAPE_TOKEN, PROPERTIES_INVALID_STRING_ESCAPE);
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(keys1.get(tokenType));
  }

  public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<TextAttributesKey, Pair<String, HighlightSeverity>>(4);
  static {
    DISPLAY_NAMES.put(KEYWORD_VALUE, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.property.value"), null));
    DISPLAY_NAMES.put(COMMENT, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.comment"), null));
    DISPLAY_NAMES.put(PROPERTIES_VALID_STRING_ESCAPE, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.valid.string.escape"), null));
    DISPLAY_NAMES.put(PROPERTIES_INVALID_STRING_ESCAPE, new Pair<String, HighlightSeverity>(OptionsBundle.message("options.properties.attribute.descriptor.invalid.string.escape"), HighlightSeverity.WARNING));
  }
}