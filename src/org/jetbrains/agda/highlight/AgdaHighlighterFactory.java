package org.jetbrains.agda.highlight;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;

public class AgdaHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {

  @NotNull
  protected AgdaHighlighter createHighlighter() {
    return new AgdaHighlighter();
  }

}