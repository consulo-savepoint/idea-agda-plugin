// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.gen.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.agda.parser.AgdaCompositeElementType;
import org.jetbrains.agda.parser.AgdaToken;
import org.jetbrains.agda.psi.impl.*;

public interface AgdaTokenTypes {

  IElementType FQ_NAME = new AgdaCompositeElementType("FQ_NAME");
  IElementType IMPORT_NAMES = new AgdaCompositeElementType("IMPORT_NAMES");
  IElementType MODULE_DECLARATION = new AgdaCompositeElementType("MODULE_DECLARATION");
  IElementType MODULE_IMPORT = new AgdaCompositeElementType("MODULE_IMPORT");

  IElementType COLON = new AgdaToken(":");
  IElementType COMMENT = new AgdaToken("COMMENT");
  IElementType DOT = new AgdaToken(".");
  IElementType END_OF_LINE_COMMENT = new AgdaToken("--");
  IElementType ID = new AgdaToken("id");
  IElementType KEYWORD = new AgdaToken("KEYWORD");
  IElementType LEFT_BRACE = new AgdaToken("{");
  IElementType LEFT_PAREN = new AgdaToken("(");
  IElementType NEW_LINE = new AgdaToken("\n");
  IElementType OP_EQ = new AgdaToken("=");
  IElementType RIGHT_BRACE = new AgdaToken("}");
  IElementType RIGHT_PAREN = new AgdaToken(")");
  IElementType SEMICOLON = new AgdaToken(";");
  IElementType SPEC_CHARACTERS = new AgdaToken("SPEC_CHARACTERS");
  IElementType STRING = new AgdaToken("STRING");
  IElementType VIRTUAL_LEFT_PAREN = new AgdaToken("VIRTUAL_LEFT_PAREN");
  IElementType VIRTUAL_RIGHT_PAREN = new AgdaToken("VIRTUAL_RIGHT_PAREN");
  IElementType VIRTUAL_SEMICOLON = new AgdaToken("VIRTUAL_SEMICOLON");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == FQ_NAME) {
        return new FqNameImpl(node);
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
