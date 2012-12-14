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

public class ModuleDeclarationImpl extends ASTWrapperPsiElement implements ModuleDeclaration {

  public ModuleDeclarationImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<Binding> getBindingList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Binding.class);
  }

  @Override
  @Nullable
  public FqName getFqName() {
    return findChildByClass(FqName.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitModuleDeclaration(this);
    else super.accept(visitor);
  }

}
