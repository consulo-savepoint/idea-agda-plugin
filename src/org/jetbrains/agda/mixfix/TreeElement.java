package org.jetbrains.agda.mixfix;


import com.intellij.psi.PsiElement;
import org.jetbrains.agda.psi.AName;

public class TreeElement {
    private final TreeElement[] myChildren;
    private final PsiElement myElement;
    private final PsiElement myDeclaration;

    public TreeElement(PsiElement element, PsiElement declaration, TreeElement... children) {
        myElement = element;
        myDeclaration = declaration;
        myChildren = children;
    }

    public String getText() {
        if (myElement instanceof AName) {
            return myElement.getText();
        }
        return null;
    }

    public TreeElement[] getChildren() {
        return myChildren;
    }

    public PsiElement getElement() {
        return myElement;
    }

    public boolean isTerm() {
        return myElement instanceof AName;
    }

    public PsiElement getDeclaration() {
        return myDeclaration;
    }

    public PsiElement getDeclaration(PsiElement element) {
        for (TreeElement child: myChildren) {
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
