package org.jetbrains.agda.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Evgeny.Kurbatsky
 */
public class AgdaNamedElement extends ASTWrapperPsiElement implements PsiNamedElement{

    public AgdaNamedElement(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return this;
    }
}
