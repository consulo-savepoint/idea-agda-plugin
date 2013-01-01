// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.gen.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.agda.parser.AgdaCompositeElementType;
import org.jetbrains.agda.parser.AgdaToken;
import org.jetbrains.agda.psi.impl.*;

public interface AgdaTokenTypes {

  IElementType ABSURD_EXPRESSION = new AgdaCompositeElementType("ABSURD_EXPRESSION");
  IElementType ABSURD_FUNCTION = new AgdaCompositeElementType("ABSURD_FUNCTION");
  IElementType APPLICATION = new AgdaCompositeElementType("APPLICATION");
  IElementType A_NAME = new AgdaCompositeElementType("A_NAME");
  IElementType BINDING = new AgdaCompositeElementType("BINDING");
  IElementType BUILD_IN_PRAGMA = new AgdaCompositeElementType("BUILD_IN_PRAGMA");
  IElementType COMPILED_PRAGMA = new AgdaCompositeElementType("COMPILED_PRAGMA");
  IElementType COMPILED_TYPE_PRAGMA = new AgdaCompositeElementType("COMPILED_TYPE_PRAGMA");
  IElementType CONSTRUCTORS = new AgdaCompositeElementType("CONSTRUCTORS");
  IElementType DATA_DECLARATION = new AgdaCompositeElementType("DATA_DECLARATION");
  IElementType EXPLICIT_TELESCOPE = new AgdaCompositeElementType("EXPLICIT_TELESCOPE");
  IElementType EXPRESSION = new AgdaCompositeElementType("EXPRESSION");
  IElementType FORALL_EXPRESSION = new AgdaCompositeElementType("FORALL_EXPRESSION");
  IElementType FQ_NAME = new AgdaCompositeElementType("FQ_NAME");
  IElementType FUNCTION_DECLARATION = new AgdaCompositeElementType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new AgdaCompositeElementType("FUNCTION_TYPE");
  IElementType FUNCTION_TYPE_DECLARATION = new AgdaCompositeElementType("FUNCTION_TYPE_DECLARATION");
  IElementType IMPLICIT_TELESCOPE = new AgdaCompositeElementType("IMPLICIT_TELESCOPE");
  IElementType IMPORT_NAME = new AgdaCompositeElementType("IMPORT_NAME");
  IElementType INFIX = new AgdaCompositeElementType("INFIX");
  IElementType LAMBDA_EXPRESSION = new AgdaCompositeElementType("LAMBDA_EXPRESSION");
  IElementType LET_EXPRESSION = new AgdaCompositeElementType("LET_EXPRESSION");
  IElementType LHS = new AgdaCompositeElementType("LHS");
  IElementType MAYBE_NEW_LINE = new AgdaCompositeElementType("MAYBE_NEW_LINE");
  IElementType MODULE_DECLARATION = new AgdaCompositeElementType("MODULE_DECLARATION");
  IElementType MODULE_IMPORT = new AgdaCompositeElementType("MODULE_IMPORT");
  IElementType NAME_DECLARATION = new AgdaCompositeElementType("NAME_DECLARATION");
  IElementType NEW_LINE = new AgdaCompositeElementType("NEW_LINE");
  IElementType NUMBER_EXPRESSION = new AgdaCompositeElementType("NUMBER_EXPRESSION");
  IElementType OPEN = new AgdaCompositeElementType("OPEN");
  IElementType PARENTHESIS_EXPRESSION = new AgdaCompositeElementType("PARENTHESIS_EXPRESSION");
  IElementType POSTULATE = new AgdaCompositeElementType("POSTULATE");
  IElementType POSTULATE_BINDINGS = new AgdaCompositeElementType("POSTULATE_BINDINGS");
  IElementType PRAGMA = new AgdaCompositeElementType("PRAGMA");
  IElementType PRAGMA_STRING = new AgdaCompositeElementType("PRAGMA_STRING");
  IElementType RECORD_CONSTRUCTOR = new AgdaCompositeElementType("RECORD_CONSTRUCTOR");
  IElementType RECORD_DECLARATION = new AgdaCompositeElementType("RECORD_DECLARATION");
  IElementType RECORD_FIELD = new AgdaCompositeElementType("RECORD_FIELD");
  IElementType RENAMING = new AgdaCompositeElementType("RENAMING");
  IElementType SUBSTITUTE_IMPLISIT = new AgdaCompositeElementType("SUBSTITUTE_IMPLISIT");
  IElementType TELESCOPE = new AgdaCompositeElementType("TELESCOPE");
  IElementType TELE_ARROW = new AgdaCompositeElementType("TELE_ARROW");
  IElementType TYPED_UNTYPED_BINDING = new AgdaCompositeElementType("TYPED_UNTYPED_BINDING");
  IElementType TYPE_SIGNATURE = new AgdaCompositeElementType("TYPE_SIGNATURE");
  IElementType TYPE_SIGNATURES = new AgdaCompositeElementType("TYPE_SIGNATURES");
  IElementType USING_OR_HIDING = new AgdaCompositeElementType("USING_OR_HIDING");

  IElementType ARROW = new AgdaToken("->");
  IElementType ASSIGNMENT = new AgdaToken("=");
  IElementType COLON = new AgdaToken(":");
  IElementType COMMENT = new AgdaToken("COMMENT");
  IElementType CONSTRUCTOR_KEYWORD = new AgdaToken("constructor");
  IElementType DATA_KEYWORD = new AgdaToken("data");
  IElementType DOT = new AgdaToken(".");
  IElementType END_OF_LINE_COMMENT = new AgdaToken("--");
  IElementType FIELD_KEYWORD = new AgdaToken("field");
  IElementType FORALL = new AgdaToken("forall");
  IElementType ID = new AgdaToken("id");
  IElementType IMPORT_KEYWORD = new AgdaToken("import");
  IElementType INFIXL_KEYWORD = new AgdaToken("infixl");
  IElementType INFIXR_KEYWORD = new AgdaToken("infixr");
  IElementType INFIX_KEYWORD = new AgdaToken("infix");
  IElementType IN_KEYWORD = new AgdaToken("in");
  IElementType LAMBDA = new AgdaToken("\\");
  IElementType LEFT_BRACE = new AgdaToken("{");
  IElementType LEFT_PAREN = new AgdaToken("(");
  IElementType LET_KEYWORD = new AgdaToken("let");
  IElementType MODULE_KEYWORD = new AgdaToken("module");
  IElementType NUMBER = new AgdaToken("number");
  IElementType OPEN_KEYWORD = new AgdaToken("open");
  IElementType POSTULATE_KEYWORD = new AgdaToken("postulate");
  IElementType PRAGMA_CLOSE = new AgdaToken("#-}");
  IElementType PRAGMA_OPEN = new AgdaToken("{-#");
  IElementType RECORD_KEYWORD = new AgdaToken("record");
  IElementType RIGHT_BRACE = new AgdaToken("}");
  IElementType RIGHT_PAREN = new AgdaToken(")");
  IElementType SEMICOLON = new AgdaToken(";");
  IElementType SPEC_CHARACTERS = new AgdaToken("SPEC_CHARACTERS");
  IElementType STRING = new AgdaToken("STRING");
  IElementType VIRTUAL_LEFT_PAREN = new AgdaToken("VIRTUAL_LEFT_PAREN");
  IElementType VIRTUAL_RIGHT_PAREN = new AgdaToken("VIRTUAL_RIGHT_PAREN");
  IElementType VIRTUAL_SEMICOLON = new AgdaToken("VIRTUAL_SEMICOLON");
  IElementType WHERE_KEYWORD = new AgdaToken("where");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ABSURD_EXPRESSION) {
        return new AbsurdExpressionImpl(node);
      }
      else if (type == ABSURD_FUNCTION) {
        return new AbsurdFunctionImpl(node);
      }
      else if (type == APPLICATION) {
        return new ApplicationImpl(node);
      }
      else if (type == A_NAME) {
        return new ANameImpl(node);
      }
      else if (type == BINDING) {
        return new BindingImpl(node);
      }
      else if (type == BUILD_IN_PRAGMA) {
        return new BuildInPragmaImpl(node);
      }
      else if (type == COMPILED_PRAGMA) {
        return new CompiledPragmaImpl(node);
      }
      else if (type == COMPILED_TYPE_PRAGMA) {
        return new CompiledTypePragmaImpl(node);
      }
      else if (type == CONSTRUCTORS) {
        return new ConstructorsImpl(node);
      }
      else if (type == DATA_DECLARATION) {
        return new DataDeclarationImpl(node);
      }
      else if (type == EXPLICIT_TELESCOPE) {
        return new ExplicitTelescopeImpl(node);
      }
      else if (type == EXPRESSION) {
        return new ExpressionImpl(node);
      }
      else if (type == FORALL_EXPRESSION) {
        return new ForallExpressionImpl(node);
      }
      else if (type == FQ_NAME) {
        return new FqNameImpl(node);
      }
      else if (type == FUNCTION_DECLARATION) {
        return new FunctionDeclarationImpl(node);
      }
      else if (type == FUNCTION_TYPE) {
        return new FunctionTypeImpl(node);
      }
      else if (type == FUNCTION_TYPE_DECLARATION) {
        return new FunctionTypeDeclarationImpl(node);
      }
      else if (type == IMPLICIT_TELESCOPE) {
        return new ImplicitTelescopeImpl(node);
      }
      else if (type == IMPORT_NAME) {
        return new ImportNameImpl(node);
      }
      else if (type == INFIX) {
        return new InfixImpl(node);
      }
      else if (type == LAMBDA_EXPRESSION) {
        return new LambdaExpressionImpl(node);
      }
      else if (type == LET_EXPRESSION) {
        return new LetExpressionImpl(node);
      }
      else if (type == LHS) {
        return new LhsImpl(node);
      }
      else if (type == MAYBE_NEW_LINE) {
        return new MaybeNewLineImpl(node);
      }
      else if (type == MODULE_DECLARATION) {
        return new ModuleDeclarationImpl(node);
      }
      else if (type == MODULE_IMPORT) {
        return new ModuleImportImpl(node);
      }
      else if (type == NAME_DECLARATION) {
        return new NameDeclarationImpl(node);
      }
      else if (type == NEW_LINE) {
        return new NewLineImpl(node);
      }
      else if (type == NUMBER_EXPRESSION) {
        return new NumberExpressionImpl(node);
      }
      else if (type == OPEN) {
        return new OpenImpl(node);
      }
      else if (type == PARENTHESIS_EXPRESSION) {
        return new ParenthesisExpressionImpl(node);
      }
      else if (type == POSTULATE) {
        return new PostulateImpl(node);
      }
      else if (type == POSTULATE_BINDINGS) {
        return new PostulateBindingsImpl(node);
      }
      else if (type == PRAGMA) {
        return new PragmaImpl(node);
      }
      else if (type == PRAGMA_STRING) {
        return new PragmaStringImpl(node);
      }
      else if (type == RECORD_CONSTRUCTOR) {
        return new RecordConstructorImpl(node);
      }
      else if (type == RECORD_DECLARATION) {
        return new RecordDeclarationImpl(node);
      }
      else if (type == RECORD_FIELD) {
        return new RecordFieldImpl(node);
      }
      else if (type == RENAMING) {
        return new RenamingImpl(node);
      }
      else if (type == SUBSTITUTE_IMPLISIT) {
        return new SubstituteImplisitImpl(node);
      }
      else if (type == TELESCOPE) {
        return new TelescopeImpl(node);
      }
      else if (type == TELE_ARROW) {
        return new TeleArrowImpl(node);
      }
      else if (type == TYPED_UNTYPED_BINDING) {
        return new TypedUntypedBindingImpl(node);
      }
      else if (type == TYPE_SIGNATURE) {
        return new TypeSignatureImpl(node);
      }
      else if (type == TYPE_SIGNATURES) {
        return new TypeSignaturesImpl(node);
      }
      else if (type == USING_OR_HIDING) {
        return new UsingOrHidingImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
