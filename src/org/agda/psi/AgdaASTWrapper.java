package org.agda.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.agda.ghci.AgdaExernalAnnotation;
import org.agda.ghci.AgdaSyntaxAnnotation;
import org.agda.ghci.AnnotationContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AgdaASTWrapper extends AgdaBaseElement {

    public AgdaASTWrapper(ASTNode node) {
        super(node);
    }


    @Override
    public PsiReference getReference() {
        List<AgdaExernalAnnotation> myAnnotations = AnnotationContainer.INSTANCE.myAnnotations;
        if (myAnnotations != null) {
            for (AgdaExernalAnnotation exernalAnnotation: myAnnotations) {
                if (!(exernalAnnotation instanceof AgdaSyntaxAnnotation)) {
                    continue;
                }
                AgdaSyntaxAnnotation annotation = (AgdaSyntaxAnnotation) exernalAnnotation;

                if (annotation.getStart() == getTextRange().getStartOffset()) {
                    if (annotation.getReference() != null) {
                        PsiElement current = this;
                        while (current instanceof AgdaBaseElement) {
                            current = current.getParent();
                        }
                        final PsiElement elementAt = current.findElementAt(annotation.getReferenceIndex());
                        if (elementAt != null) {
                            return new PsiReference() {
                                public PsiElement getElement() {
                                    return AgdaASTWrapper.this;
                                }

                                public TextRange getRangeInElement() {
                                    return new TextRange(0, getTextLength());
                                }

                                public PsiElement resolve() {
                                    return elementAt;
                                }

                                @NotNull
                                public String getCanonicalText() {
                                    return getText();
                                }

                                public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
                                    throw new IncorrectOperationException();
                                }

                                public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
                                    throw new IncorrectOperationException();
                                }

                                public boolean isReferenceTo(PsiElement element) {
                                    return elementAt == element;
                                }

                                @NotNull
                                public Object[] getVariants() {
                                    return new Object[0];
                                }

                                public boolean isSoft() {
                                    return false;
                                }
                            };
                        }

                    }
                }
            }
        }
        return null;
    }
}
