// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ModuleDeclaration extends PsiElement {

  @NotNull
  List<AbsurdFunction> getAbsurdFunctionList();

  @Nullable
  Bindings getBindings();

  @NotNull
  List<DataDeclaration> getDataDeclarationList();

  @Nullable
  FqName getFqName();

  @NotNull
  List<FunctionDeclaration> getFunctionDeclarationList();

  @NotNull
  List<FunctionTypeDeclaration> getFunctionTypeDeclarationList();

  @NotNull
  List<Infix> getInfixList();

  @NotNull
  List<ModuleDeclaration> getModuleDeclarationList();

  @NotNull
  List<ModuleImport> getModuleImportList();

  @NotNull
  List<Open> getOpenList();

  @NotNull
  List<Postulate> getPostulateList();

  @NotNull
  List<Pragma> getPragmaList();

  @NotNull
  List<RecordConstructor> getRecordConstructorList();

  @NotNull
  List<RecordDeclaration> getRecordDeclarationList();

  @NotNull
  List<RecordField> getRecordFieldList();

}
