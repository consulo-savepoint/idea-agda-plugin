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

public class ExpressionImpl extends ASTWrapperPsiElement implements Expression {

  public ExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public ArrowExpression getArrowExpression() {
    return findChildByClass(ArrowExpression.class);
  }

  @Override
  @Nullable
  public ForallExpression getForallExpression() {
    return findChildByClass(ForallExpression.class);
  }

  @Override
  @Nullable
  public FqName getFqName() {
    return findChildByClass(FqName.class);
  }

  @Override
  @Nullable
  public GoalExpression getGoalExpression() {
    return findChildByClass(GoalExpression.class);
  }

  @Override
  @Nullable
  public LetExpression getLetExpression() {
    return findChildByClass(LetExpression.class);
  }

  @Override
  @Nullable
  public NumberExpression getNumberExpression() {
    return findChildByClass(NumberExpression.class);
  }

  @Override
  @Nullable
  public SubstituteImplisit getSubstituteImplisit() {
    return findChildByClass(SubstituteImplisit.class);
  }

  @Override
  @Nullable
  public TeleArrow getTeleArrow() {
    return findChildByClass(TeleArrow.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitExpression(this);
    else super.accept(visitor);
  }

}
