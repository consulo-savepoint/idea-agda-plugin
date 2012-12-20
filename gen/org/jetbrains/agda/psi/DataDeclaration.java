// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DataDeclaration extends PsiElement {

  @NotNull
  List<Binding> getBindingList();

  @Nullable
  Constructors getConstructors();

  @Nullable
  Expression getExpression();

  @Nullable
  NameDeclaration getNameDeclaration();

}
