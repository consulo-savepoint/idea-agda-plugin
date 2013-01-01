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

public class RecordDeclarationImpl extends ASTWrapperPsiElement implements RecordDeclaration {

  public RecordDeclarationImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<AbsurdFunction> getAbsurdFunctionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AbsurdFunction.class);
  }

  @Override
  @NotNull
  public List<DataDeclaration> getDataDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DataDeclaration.class);
  }

  @Override
  @Nullable
  public Expression getExpression() {
    return findChildByClass(Expression.class);
  }

  @Override
  @NotNull
  public List<FunctionDeclaration> getFunctionDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FunctionDeclaration.class);
  }

  @Override
  @NotNull
  public List<FunctionTypeDeclaration> getFunctionTypeDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FunctionTypeDeclaration.class);
  }

  @Override
  @NotNull
  public List<Infix> getInfixList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Infix.class);
  }

  @Override
  @NotNull
  public List<MaybeNewLine> getMaybeNewLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MaybeNewLine.class);
  }

  @Override
  @NotNull
  public List<ModuleDeclaration> getModuleDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ModuleDeclaration.class);
  }

  @Override
  @NotNull
  public List<ModuleImport> getModuleImportList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ModuleImport.class);
  }

  @Override
  @Nullable
  public NameDeclaration getNameDeclaration() {
    return findChildByClass(NameDeclaration.class);
  }

  @Override
  @NotNull
  public List<Open> getOpenList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Open.class);
  }

  @Override
  @NotNull
  public List<Postulate> getPostulateList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Postulate.class);
  }

  @Override
  @NotNull
  public List<Pragma> getPragmaList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Pragma.class);
  }

  @Override
  @NotNull
  public List<RecordConstructor> getRecordConstructorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RecordConstructor.class);
  }

  @Override
  @NotNull
  public List<RecordDeclaration> getRecordDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RecordDeclaration.class);
  }

  @Override
  @NotNull
  public List<RecordField> getRecordFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RecordField.class);
  }

  @Override
  @NotNull
  public List<TypedUntypedBinding> getTypedUntypedBindingList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, TypedUntypedBinding.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitRecordDeclaration(this);
    else super.accept(visitor);
  }

}
