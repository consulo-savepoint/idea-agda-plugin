package org.jetbrains.agda.mixfix;


import com.intellij.psi.PsiElement;
import org.jetbrains.agda.psi.AgdaReferenceElementImpl;

public class Term {
    private final String myText;
    private final Term[] myChildren;
    private final PsiElement myElement;
    private final PsiElement myDeclaration;

    public Term(String text, PsiElement element, PsiElement declaration, Term ... children) {
        myText = text;
        myElement = element;
        myDeclaration = declaration;
        myChildren = children;
    }

    public String getText() {
        return myText;
    }

    public Term[] getChildren() {
        return myChildren;
    }

    public PsiElement getElement() {
        return myElement;
    }

    public boolean isTerm() {
        return myText != null;
    }

    public PsiElement getDeclaration(PsiElement element) {
        for (Term child: myChildren) {
            if (child.getElement() == element) {
                return myDeclaration;
            }
            PsiElement declaration = child.getDeclaration(element);
            if (declaration != null) {
                return declaration;
            }
        }
        return null;
    }
}
