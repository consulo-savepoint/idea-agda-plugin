// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class Visitor extends PsiElementVisitor {

  public void visitAbsurdExpression(@NotNull AbsurdExpression o) {
    visitExpression(o);
  }

  public void visitAbsurdFunction(@NotNull AbsurdFunction o) {
    visitPsiElement(o);
  }

  public void visitApplication(@NotNull Application o) {
    visitExpression(o);
  }

  public void visitArrowExpression(@NotNull ArrowExpression o) {
    visitPsiElement(o);
  }

  public void visitAsName(@NotNull AsName o) {
    visitPsiElement(o);
  }

  public void visitBinding(@NotNull Binding o) {
    visitPsiElement(o);
  }

  public void visitBindings(@NotNull Bindings o) {
    visitPsiElement(o);
  }

  public void visitBuildInPragma(@NotNull BuildInPragma o) {
    visitPsiElement(o);
  }

  public void visitCompiledPragma(@NotNull CompiledPragma o) {
    visitPsiElement(o);
  }

  public void visitCompiledTypePragma(@NotNull CompiledTypePragma o) {
    visitPsiElement(o);
  }

  public void visitConstructors(@NotNull Constructors o) {
    visitPsiElement(o);
  }

  public void visitDataDeclaration(@NotNull DataDeclaration o) {
    visitPsiElement(o);
  }

  public void visitExplicitTelescope(@NotNull ExplicitTelescope o) {
    visitTelescope(o);
  }

  public void visitExpression(@NotNull Expression o) {
    visitPsiElement(o);
  }

  public void visitForallExpression(@NotNull ForallExpression o) {
    visitPsiElement(o);
  }

  public void visitFqName(@NotNull FqName o) {
    visitPsiElement(o);
  }

  public void visitFunctionDeclaration(@NotNull FunctionDeclaration o) {
    visitPsiElement(o);
  }

  public void visitFunctionTypeDeclaration(@NotNull FunctionTypeDeclaration o) {
    visitPsiElement(o);
  }

  public void visitGoalExpression(@NotNull GoalExpression o) {
    visitPsiElement(o);
  }

  public void visitImplicitTelescope(@NotNull ImplicitTelescope o) {
    visitTelescope(o);
  }

  public void visitImportName(@NotNull ImportName o) {
    visitPsiElement(o);
  }

  public void visitInfix(@NotNull Infix o) {
    visitPsiElement(o);
  }

  public void visitLambdaExpression(@NotNull LambdaExpression o) {
    visitExpression(o);
  }

  public void visitLetExpression(@NotNull LetExpression o) {
    visitPsiElement(o);
  }

  public void visitLhs(@NotNull Lhs o) {
    visitPsiElement(o);
  }

  public void visitModuleArguments(@NotNull ModuleArguments o) {
    visitPsiElement(o);
  }

  public void visitModuleDeclaration(@NotNull ModuleDeclaration o) {
    visitPsiElement(o);
  }

  public void visitModuleImport(@NotNull ModuleImport o) {
    visitPsiElement(o);
  }

  public void visitMutual(@NotNull Mutual o) {
    visitPsiElement(o);
  }

  public void visitNameDeclaration(@NotNull NameDeclaration o) {
    visitPsiElement(o);
  }

  public void visitNumberExpression(@NotNull NumberExpression o) {
    visitPsiElement(o);
  }

  public void visitOpen(@NotNull Open o) {
    visitPsiElement(o);
  }

  public void visitParenthesisExpression(@NotNull ParenthesisExpression o) {
    visitExpression(o);
  }

  public void visitPostulate(@NotNull Postulate o) {
    visitPsiElement(o);
  }

  public void visitPostulateBindings(@NotNull PostulateBindings o) {
    visitPsiElement(o);
  }

  public void visitPragma(@NotNull Pragma o) {
    visitPsiElement(o);
  }

  public void visitPragmaString(@NotNull PragmaString o) {
    visitPsiElement(o);
  }

  public void visitRecordConstructor(@NotNull RecordConstructor o) {
    visitPsiElement(o);
  }

  public void visitRecordDeclaration(@NotNull RecordDeclaration o) {
    visitPsiElement(o);
  }

  public void visitRecordField(@NotNull RecordField o) {
    visitPsiElement(o);
  }

  public void visitRenaming(@NotNull Renaming o) {
    visitPsiElement(o);
  }

  public void visitSubstituteImplisit(@NotNull SubstituteImplisit o) {
    visitPsiElement(o);
  }

  public void visitTeleArrow(@NotNull TeleArrow o) {
    visitPsiElement(o);
  }

  public void visitTelescope(@NotNull Telescope o) {
    visitPsiElement(o);
  }

  public void visitTypeSignature(@NotNull TypeSignature o) {
    visitPsiElement(o);
  }

  public void visitTypeSignatures(@NotNull TypeSignatures o) {
    visitPsiElement(o);
  }

  public void visitTypedUntypedBinding(@NotNull TypedUntypedBinding o) {
    visitPsiElement(o);
  }

  public void visitUsingOrHiding(@NotNull UsingOrHiding o) {
    visitPsiElement(o);
  }

  public void visitWhereExpression(@NotNull WhereExpression o) {
    visitPsiElement(o);
  }

  public void visitWherePart(@NotNull WherePart o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
