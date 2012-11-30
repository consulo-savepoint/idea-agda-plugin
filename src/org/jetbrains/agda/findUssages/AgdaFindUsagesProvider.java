package org.jetbrains.agda.findUssages;

import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.psi.PsiElement;
import com.intellij.find.impl.HelpID;
import org.jetbrains.agda.psi.AgdaASTWrapper;
import org.jetbrains.annotations.NotNull;


public class AgdaFindUsagesProvider implements FindUsagesProvider {
  public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
    return psiElement instanceof AgdaASTWrapper;
  }

  public String getHelpId(@NotNull PsiElement psiElement) {
    return HelpID.FIND_OTHER_USAGES;
  }

  @NotNull
  public String getType(@NotNull PsiElement element) {
    return "";
  }

  @NotNull
  public String getDescriptiveName(@NotNull PsiElement element) {
      String name = element.getText();
      if (name.startsWith("_")) {
          name = name.substring(1);
      }
      if (name.endsWith("_")) {
          name = name.substring(0, name.length() - 1);
      }
      return name;
  }

  @NotNull
  public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
      return getDescriptiveName(element);
  }

  public WordsScanner getWordsScanner() {
    return new AgdaWordsScanner();
  }
}
