package org.agda.parser;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AgdaElementType<T extends PsiElement> extends IElementType {
    private final Constructor<T> constructor;

    public AgdaElementType(@NonNls String debugName, Class<T> clazz) {
        super(debugName, AgdaLanguage.INSTANCE);
        try {
            constructor = clazz.getConstructor(ASTNode.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException();
        }
    }

    public T createPsy(ASTNode node) {
        if (constructor != null) {
            try {
                return constructor.newInstance(node);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Agda:" + super.toString();
    }
}