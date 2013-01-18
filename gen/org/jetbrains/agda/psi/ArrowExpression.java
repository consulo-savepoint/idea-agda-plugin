// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ArrowExpression extends PsiElement {

  @NotNull
  List<Expression> getExpressionList();

  @Nullable
  FqName getFqName();

  @Nullable
  GoalExpression getGoalExpression();

  @Nullable
  NumberExpression getNumberExpression();

  @Nullable
  SubstituteImplisit getSubstituteImplisit();

}
