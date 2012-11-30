package org.jetbrains.agda.psi;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.agda.AgdaLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AgdaBaseElement extends ASTDelegatePsiElement {
    private final ASTNode myNode;

    public AgdaBaseElement(ASTNode node) {
        myNode = node;
    }

    @NotNull
    public Language getLanguage() {
        return AgdaLanguage.INSTANCE;
    }

    public PsiElement getParent() {
        return  SharedImplUtil.getParent(getNode());
    }

    public int getTextOffset() {
        return myNode.getStartOffset();
    }

    public String getText() {
        return myNode.getText();
    }

    @NotNull
    public char[] textToCharArray() {
        throw new RuntimeException();
    }

    public PsiElement copy() {
        throw new RuntimeException();
    }

    public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public PsiElement addBefore(@NotNull PsiElement element, @Nullable PsiElement anchor) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public PsiElement addAfter(@NotNull PsiElement element, @Nullable PsiElement anchor) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public void checkAdd(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public void delete() throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    public ASTNode getNode() {
        return myNode;
    }

}
