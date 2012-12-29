// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface FunctionType extends PsiElement {

  @Nullable
  AName getAName();

  @Nullable
  Application getApplication();

  @NotNull
  List<Expression> getExpressionList();

}
