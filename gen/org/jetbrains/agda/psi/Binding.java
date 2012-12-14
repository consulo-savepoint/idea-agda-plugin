// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface Binding extends PsiElement {

  @Nullable
  Expression getExpression();

  @NotNull
  List<NameDeclaration> getNameDeclarationList();

  @NotNull
  NewLine getNewLine();

}
