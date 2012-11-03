package org.agda.parser;

import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.psi.tree.IElementType;
import org.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

public class AgdaElementType extends IElementType {

  public AgdaElementType(@NonNls String debugName) {
    super(debugName, AgdaLanguage.INSTANCE);
  }

  @SuppressWarnings({"HardCodedStringLiteral"})
  public String toString() {
    return "Agda:" + super.toString();
  }
}