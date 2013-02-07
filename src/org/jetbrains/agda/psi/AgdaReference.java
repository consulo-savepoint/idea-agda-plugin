package org.jetbrains.agda.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.agda.scope.AgdaGlobalScope;
import org.jetbrains.annotations.NotNull;

public class AgdaReference implements PsiReference {
    private AgdaReferenceElementImpl myElement;

    public AgdaReference(AgdaReferenceElementImpl element) {
        myElement = element;
    }

    @Override
    public AgdaReferenceElementImpl getElement() {
        return myElement;
    }

    @Override
    public TextRange getRangeInElement() {
        TextRange range = myElement.getTextRange();
        return new TextRange(0, range.getLength());
    }

    @Override
    public PsiElement resolve() {
        if (getElement() instanceof FqName) {
            if (getElement().getParent() instanceof ModuleImport) {
                return new AgdaGlobalScope().find((FqName) getElement());
            }
        }
        return org.jetbrains.agda.scope.ScopePackage.findDeclaration(getElement());
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return getElement().getText();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return element.getManager().areElementsEquivalent(resolve(), element);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
