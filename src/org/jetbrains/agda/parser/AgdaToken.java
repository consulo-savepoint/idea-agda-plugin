package org.jetbrains.agda.parser;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

public class AgdaToken extends IElementType {

    public AgdaToken(@NonNls String debugName) {
        super(debugName, AgdaLanguage.INSTANCE);
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Agda Token:" + super.toString();
    }
}