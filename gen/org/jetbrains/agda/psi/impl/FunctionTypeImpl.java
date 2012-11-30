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

public class FunctionTypeImpl extends ASTWrapperPsiElement implements FunctionType {

  public FunctionTypeImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<AName> getANameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AName.class);
  }

  @Override
  @NotNull
  public List<Application> getApplicationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Application.class);
  }

  @Override
  @NotNull
  public List<Expression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Expression.class);
  }

  @Override
  @Nullable
  public FunctionType getFunctionType() {
    return findChildByClass(FunctionType.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitFunctionType(this);
    else super.accept(visitor);
  }

}
