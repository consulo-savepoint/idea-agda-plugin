package org.agda.findUssages;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.tree.TokenSet;
import org.agda.parser.AgdaLexer;
import org.agda.parser.AgdaTokenTypes;

public class AgdaWordsScanner extends DefaultWordsScanner {
  public AgdaWordsScanner() {
    super(new AgdaLexer(), TokenSet.create(AgdaTokenTypes.VALUE_CHARACTERS),
            AgdaTokenTypes.COMMENTS, TokenSet.create(AgdaTokenTypes.SPEC_CHARACTERS));
  }
}