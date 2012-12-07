package org.jetbrains.agda.core;

import com.intellij.psi.PsiElement;

public class CRefExpression extends CExpression {
    private PsiElement myDeclaration;
    private String myText;

    public CRefExpression(PsiElement declaration, String text) {
        myDeclaration = declaration;
        myText = text;
    }


    public PsiElement getDeclaration() {
        return myDeclaration;
    }

    public String getText() {
        return myText;
    }

    @Override
    public String toString() {
        return myText;
    }
}
