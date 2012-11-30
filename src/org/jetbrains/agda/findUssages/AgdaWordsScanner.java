package org.jetbrains.agda.findUssages;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.agda.parser.AgdaLexer;
import org.jetbrains.agda.parser.AgdaTokenTypes;

public class AgdaWordsScanner extends DefaultWordsScanner {
  public AgdaWordsScanner() {
    super(new AgdaLexer(), TokenSet.create(AgdaTokenTypes.NAME),
            AgdaTokenTypes.COMMENTS, TokenSet.create(AgdaTokenTypes.SPEC_CHARACTERS));
  }
}