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

public class ApplicationImpl extends ExpressionImpl implements Application {

  public ApplicationImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<Expression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Expression.class);
  }

  @Override
  @NotNull
  public List<FqName> getFqNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, FqName.class);
  }

  @Override
  @NotNull
  public List<GoalExpression> getGoalExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GoalExpression.class);
  }

  @Override
  @NotNull
  public List<NumberExpression> getNumberExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NumberExpression.class);
  }

  @Override
  @NotNull
  public List<SubstituteImplisit> getSubstituteImplisitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SubstituteImplisit.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitApplication(this);
    else super.accept(visitor);
  }

}
