// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.jetbrains.agda.gen.parser.AgdaTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.jetbrains.agda.psi.*;

public class UsingOrHidingImpl extends ASTWrapperPsiElement implements UsingOrHiding {

  public UsingOrHidingImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public ImportNames getImportNames() {
    return findNotNullChildByClass(ImportNames.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitUsingOrHiding(this);
    else super.accept(visitor);
  }

}
