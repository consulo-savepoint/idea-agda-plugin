// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class Visitor extends PsiElementVisitor {

  public void visitFqName(@NotNull FqName o) {
    visitPsiElement(o);
  }

  public void visitImportNames(@NotNull ImportNames o) {
    visitPsiElement(o);
  }

  public void visitModuleDeclaration(@NotNull ModuleDeclaration o) {
    visitPsiElement(o);
  }

  public void visitModuleImport(@NotNull ModuleImport o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
