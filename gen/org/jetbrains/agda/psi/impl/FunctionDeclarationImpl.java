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

public class FunctionDeclarationImpl extends ASTWrapperPsiElement implements FunctionDeclaration {

  public FunctionDeclarationImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public Lhs getLhs() {
    return findNotNullChildByClass(Lhs.class);
  }

  @Override
  @Nullable
  public WhereExpression getWhereExpression() {
    return findChildByClass(WhereExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) ((Visitor)visitor).visitFunctionDeclaration(this);
    else super.accept(visitor);
  }

}
