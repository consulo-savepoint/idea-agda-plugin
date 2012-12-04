package org.jetbrains.agda.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;

public class AgdaReferenceElementImpl extends ASTWrapperPsiElement {

    public AgdaReferenceElementImpl(@org.jetbrains.annotations.NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new AgdaReference(this);
    }
}
