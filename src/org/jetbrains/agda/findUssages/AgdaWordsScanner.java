package org.jetbrains.agda.findUssages;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;
import org.jetbrains.agda.parser.AgdaHighlightLexer;
import org.jetbrains.agda.parser.AgdaTokenSets;

public class AgdaWordsScanner extends DefaultWordsScanner {
  public AgdaWordsScanner() {
    super(new AgdaHighlightLexer(), TokenSet.create(AgdaTokenTypes.ID),
            AgdaTokenSets.COMMENTS, TokenSet.create());
  }
}