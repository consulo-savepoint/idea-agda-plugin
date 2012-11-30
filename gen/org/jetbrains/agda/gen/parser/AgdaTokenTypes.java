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
  IElementType CONSTRUCTOR = new AgdaCompositeElementType("CONSTRUCTOR");
  IElementType CONSTRUCTORS = new AgdaCompositeElementType("CONSTRUCTORS");
  IElementType DATA_DECLARATION = new AgdaCompositeElementType("DATA_DECLARATION");
  IElementType EXPRESSION = new AgdaCompositeElementType("EXPRESSION");
  IElementType FQ_NAME = new AgdaCompositeElementType("FQ_NAME");
  IElementType FUNCTION_DECLARATION = new AgdaCompositeElementType("FUNCTION_DECLARATION");
  IElementType FUNCTION_TYPE = new AgdaCompositeElementType("FUNCTION_TYPE");
  IElementType FUNCTION_TYPE_DECLARATION = new AgdaCompositeElementType("FUNCTION_TYPE_DECLARATION");
  IElementType IMPORT_NAMES = new AgdaCompositeElementType("IMPORT_NAMES");
  IElementType MODULE_DECLARATION = new AgdaCompositeElementType("MODULE_DECLARATION");
  IElementType MODULE_IMPORT = new AgdaCompositeElementType("MODULE_IMPORT");

  IElementType ASSIGNMENT = new AgdaToken("=");
  IElementType COLON = new AgdaToken(":");
  IElementType COMMENT = new AgdaToken("COMMENT");
  IElementType DATA_KEYWORD = new AgdaToken("data");
  IElementType DOT = new AgdaToken(".");
  IElementType END_OF_LINE_COMMENT = new AgdaToken("--");
  IElementType ID = new AgdaToken("id");
  IElementType KEYWORD = new AgdaToken("KEYWORD");
  IElementType LEFT_BRACE = new AgdaToken("{");
  IElementType LEFT_PAREN = new AgdaToken("(");
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
      else if (type == CONSTRUCTOR) {
        return new ConstructorImpl(node);
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
      else if (type == IMPORT_NAMES) {
        return new ImportNamesImpl(node);
      }
      else if (type == MODULE_DECLARATION) {
        return new ModuleDeclarationImpl(node);
      }
      else if (type == MODULE_IMPORT) {
        return new ModuleImportImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
