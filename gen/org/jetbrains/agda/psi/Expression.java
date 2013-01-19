// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface Expression extends PsiElement {

  @Nullable
  ArrowExpression getArrowExpression();

  @Nullable
  ForallExpression getForallExpression();

  @Nullable
  FqName getFqName();

  @Nullable
  LetExpression getLetExpression();

  @Nullable
  TeleArrow getTeleArrow();

}
