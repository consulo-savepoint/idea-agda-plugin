// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.jetbrains.agda.gen.parser.AgdaTokenTypes.*;
import org.jetbrains.agda.psi.*;

public class LambdaExpressionImpl extends ExpressionImpl implements LambdaExpression {

  public LambdaExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<Expression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Expression.class);
  }

  @Override
  @NotNull
  public List<NameDeclaration> getNameDeclarationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NameDeclaration.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitLambdaExpression(this);
    else super.accept(visitor);
  }

}
