package org.agda.psi;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.PsiElementBase;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.IncorrectOperationException;
import org.agda.AgdaLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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
