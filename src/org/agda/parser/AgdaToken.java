package org.agda.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

import java.lang.reflect.Constructor;

public class AgdaToken extends IElementType {

    public AgdaToken(@NonNls String debugName) {
        super(debugName, AgdaLanguage.INSTANCE);
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Agda Token:" + super.toString();
    }
}