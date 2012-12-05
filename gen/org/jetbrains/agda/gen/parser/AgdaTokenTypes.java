// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.gen.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.agda.parser.AgdaCompositeElementType;
import org.jetbrains.agda.parser.AgdaToken;
import org.jetbrains.agda.psi.impl.*;

public interface AgdaTokenTypes {

  IElementType APPLICATION = new AgdaCompositeElementType("APPLICATION");
  IElementType A_NAME = new AgdaCompositeElementType("A_NAME");
  IElementType BINDING = new AgdaCompositeElementType("BINDING");
  IElementType CONSTRUCTORS = new AgdaCompositeElementType("CONSTRUCTORS");
  IElementType DATA_DECLARATION = new AgdaCompositeElementType("DATA_DECLARATION");
  IElementType EXPRESSION = new AgdaCompositeElementType("EXPRESSION");
  IElementType FORALL_EXPRESSION = new AgdaCompositeElementType("FORALL_EXPRESSION");
  IElementType FQ_NAME = new AgdaCompositeElementType("FQ_NAME");
  IElementType FUNCTION_DECLARATION = new AgdaCompositeElementType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new AgdaCompositeElementType("FUNCTION_TYPE");
  IElementType FUNCTION_TYPE_DECLARATION = new AgdaCompositeElementType("FUNCTION_TYPE_DECLARATION");
  IElementType LAMBDA_EXPRESSION = new AgdaCompositeElementType("LAMBDA_EXPRESSION");
  IElementType LET_EXPRESSION = new AgdaCompositeElementType("LET_EXPRESSION");
  IElementType MODULE_DECLARATION = new AgdaCompositeElementType("MODULE_DECLARATION");
  IElementType MODULE_IMPORT = new AgdaCompositeElementType("MODULE_IMPORT");
  IElementType NAME_DECLARATION = new AgdaCompositeElementType("NAME_DECLARATION");
  IElementType NEW_LINE = new AgdaCompositeElementType("NEW_LINE");
  IElementType RENAMING = new AgdaCompositeElementType("RENAMING");
  IElementType TELESCOPE = new AgdaCompositeElementType("TELESCOPE");
  IElementType TELE_ARROW = new AgdaCompositeElementType("TELE_ARROW");
  IElementType TYPED_UNTYPED_BINDING = new AgdaCompositeElementType("TYPED_UNTYPED_BINDING");
  IElementType TYPE_SIGNATURE = new AgdaCompositeElementType("TYPE_SIGNATURE");
  IElementType USING_OR_HIDING = new AgdaCompositeElementType("USING_OR_HIDING");

  IElementType ARROW = new AgdaToken("->");
  IElementType ASSIGNMENT = new AgdaToken("=");
  IElementType COLON = new AgdaToken(":");
  IElementType COMMENT = new AgdaToken("COMMENT");
  IElementType DATA_KEYWORD = new AgdaToken("data");
  IElementType DOT = new AgdaToken(".");
  IElementType END_OF_LINE_COMMENT = new AgdaToken("--");
  IElementType FORALL = new AgdaToken("forall");
  IElementType ID = new AgdaToken("id");
  IElementType IN_KEYWORD = new AgdaToken("in");
  IElementType LAMBDA = new AgdaToken("\\");
  IElementType LEFT_BRACE = new AgdaToken("{");
  IElementType LEFT_PAREN = new AgdaToken("(");
  IElementType LET_KEYWORD = new AgdaToken("let");
  IElementType MODULE_KEYWORD = new AgdaToken("module");
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
       if (type == APPLICATION) {
        return new ApplicationImpl(node);
      }
      else if (type == A_NAME) {
        return new ANameImpl(node);
      }
      else if (type == BINDING) {
        return new BindingImpl(node);
      }
      else if (type == CONSTRUCTORS) {
        return new ConstructorsImpl(node);
      }
      else if (type == DATA_DECLARATION) {
        return new DataDeclarationImpl(node);
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
      else if (type == LAMBDA_EXPRESSION) {
        return new LambdaExpressionImpl(node);
      }
      else if (type == LET_EXPRESSION) {
        return new LetExpressionImpl(node);
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
      else if (type == RENAMING) {
        return new RenamingImpl(node);
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
      else if (type == USING_OR_HIDING) {
        return new UsingOrHidingImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
