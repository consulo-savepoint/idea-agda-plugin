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

public class ModuleImportImpl extends ASTWrapperPsiElement implements ModuleImport {

  public ModuleImportImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public AsName getAsName() {
    return findChildByClass(AsName.class);
  }

  @Override
  @Nullable
  public FqName getFqName() {
    return findChildByClass(FqName.class);
  }

  @Override
  @Nullable
  public ModuleArguments getModuleArguments() {
    return findChildByClass(ModuleArguments.class);
  }

  @Override
  @Nullable
  public Renaming getRenaming() {
    return findChildByClass(Renaming.class);
  }

  @Override
  @Nullable
  public UsingOrHiding getUsingOrHiding() {
    return findChildByClass(UsingOrHiding.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitModuleImport(this);
    else super.accept(visitor);
  }

}
