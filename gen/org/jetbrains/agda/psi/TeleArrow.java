// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TeleArrow extends PsiElement {

  @NotNull
  Expression getExpression();

  @NotNull
  List<Telescope> getTelescopeList();

}
