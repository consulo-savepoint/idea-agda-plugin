package org.agda.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.agda.ghci.AgdaExternalAnnotation;
import org.agda.ghci.AgdaSyntaxAnnotation;
import org.agda.ghci.GhciProjectComponent;
import org.agda.ghci.LaunchAgda;
import org.agda.highlight.AgdaAnnotator;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;


public class AgdaASTWrapper extends AgdaBaseElement implements PsiNamedElement {
    public volatile boolean isLoaded = false;

    public AgdaASTWrapper(ASTNode node) {
        super(node);
    }


    @Override
    public PsiReference getReference() {
        if (!isLoaded) {
            loadAnnotations();
        }
        PsiElement current = this;
        while (!(current instanceof PsiFile)) {
            current = current.getParent();
        }
        AgdaSyntaxAnnotation syntaxAnnotation = getUserData(AgdaSyntaxAnnotation.SYNTAX);

        if (syntaxAnnotation == null) {
            return null;
        }

        if (syntaxAnnotation.getReference() != null) {
            return createReference(syntaxAnnotation, getProject());
        }
        return null;
    }

    private void loadAnnotations() {
        PsiElement element = this;
        while (!(element instanceof PsiFile)) {
            element = element.getParent();
        }
        PsiFile psiFile = (PsiFile) element;

        VirtualFile file = psiFile.getVirtualFile();
        if (file == null)
            return;
        synchronized (getProject().getComponent(GhciProjectComponent.class)) {
            if (isLoaded) {
                return;
            }
            List<AgdaExternalAnnotation> agdaExternalAnnotations = LaunchAgda.load(file.getPath(), psiFile.getProject());
            if (agdaExternalAnnotations != null) {
                AgdaAnnotator.applyAnnotations(psiFile, agdaExternalAnnotations);
            }
        }

        isLoaded = true;
    }

    private PsiReference createReference(AgdaSyntaxAnnotation annotation, Project project) {
        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(new File(annotation.getReference()));
        PsiElement current = PsiManager.getInstance(project).findFile(virtualFile);
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
        return null;
    }


    @Override
    public String getName() {
        String name = getText();
        if (name.startsWith("_")) {
            name = name.substring(1);
        }
        if (name.endsWith("_")) {
            name = name.substring(0, name.length() - 1);
        }
        return name;
    }

    @Override
    public PsiElement getFirstChild() {
        ASTNode firstChildNode = getNode().getFirstChildNode();
        if (firstChildNode instanceof LeafPsiElement) {
            return null;
        }
        return super.getFirstChild();
    }

    @Override
    public PsiElement findElementAt(int offset) {
        PsiElement elementAt = super.findElementAt(offset);
        if (elementAt instanceof LeafPsiElement) {
            return elementAt.getParent();
        }
        return elementAt;
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }
}
