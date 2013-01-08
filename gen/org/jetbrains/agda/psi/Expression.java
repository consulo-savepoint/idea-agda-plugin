// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface Expression extends PsiElement {

  @Nullable
  Application getApplication();

  @Nullable
  ForallExpression getForallExpression();

  @Nullable
  FqName getFqName();

  @Nullable
  FunctionType getFunctionType();

  @Nullable
  GoalExpression getGoalExpression();

  @Nullable
  LetExpression getLetExpression();

  @Nullable
  NumberExpression getNumberExpression();

  @Nullable
  SubstituteImplisit getSubstituteImplisit();

  @Nullable
  TeleArrow getTeleArrow();

}
