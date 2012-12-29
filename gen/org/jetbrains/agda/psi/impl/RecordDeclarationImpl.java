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
  public List<Binding> getBindingList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Binding.class);
  }

  @Override
  @Nullable
  public Expression getExpression() {
    return findChildByClass(Expression.class);
  }

  @Override
  @Nullable
  public NameDeclaration getNameDeclaration() {
    return findChildByClass(NameDeclaration.class);
  }

  @Override
  @NotNull
  public List<RecordConstructor> getRecordConstructorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RecordConstructor.class);
  }

  @Override
  @NotNull
  public List<RecordField> getRecordFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RecordField.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitRecordDeclaration(this);
    else super.accept(visitor);
  }

}
