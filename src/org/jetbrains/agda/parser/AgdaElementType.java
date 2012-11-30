package org.jetbrains.agda.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

import java.lang.reflect.Constructor;

public class AgdaElementType<T extends PsiElement> extends IElementType {
    private Constructor<T> constructor = null;

    public AgdaElementType(@NonNls String debugName, Class<T> clazz) {
        super(debugName, AgdaLanguage.INSTANCE);
        if (clazz != null) {
            try {
                constructor = clazz.getConstructor(ASTNode.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException();
            }
        }
    }

    public T createPsi(ASTNode node) {
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