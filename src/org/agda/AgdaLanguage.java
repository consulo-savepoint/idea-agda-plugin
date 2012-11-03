package org.agda;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import org.agda.highlight.AgdaHighlighter;
import org.jetbrains.annotations.NotNull;

public class AgdaLanguage extends Language {
  public static final AgdaLanguage INSTANCE = new AgdaLanguage();

  public AgdaLanguage() {
    super("Agda", "text/agda");
      SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
          @NotNull
          protected SyntaxHighlighter createHighlighter() {
              return new AgdaHighlighter();
          }
      });
  }
}