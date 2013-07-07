package org.jetbrains.agda.highlight;


import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.psi.*;
import org.jetbrains.agda.psi.impl.FqNameImpl;
import org.jetbrains.agda.scope.ScopePackage;
import org.jetbrains.annotations.NotNull;

public class AgdaAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DataDeclaration) {
            DataDeclaration data = ((DataDeclaration)element);
            PsiElement id = data.getNameDeclaration();
            holder.createInfoAnnotation(id, null).setTextAttributes(AgdaHighlighter.TYPE);
            Constructors constructors = data.getConstructors();
            if (constructors != null) {
                for (TypeSignature typeSignature : constructors.getTypeSignatureList()) {
                    for (NameDeclaration nameDeclaration : typeSignature.getNameDeclarationList()) {
                        holder.createInfoAnnotation(nameDeclaration, null).setTextAttributes(AgdaHighlighter.CONSTRUCTOR);
                    }
                }
            }
        }
        if (element instanceof FqNameImpl) {
            if (element.getParent() instanceof ModuleDeclaration) {
                return;
            }
            if (element.getParent() instanceof ModuleImport) {
                return;
            }
            if ("_".equals(element.getText())) {
                return;
            }
            if ("Set".equals(element.getText())) {
                holder.createInfoAnnotation(element, null).setTextAttributes(AgdaHighlighter.TYPE);
                return;
            }
            if (hasAbsurdParent(element)) {
                return;
            }
            PsiElement declaration = ScopePackage.findDeclaration((FqNameImpl) element);
            if (declaration != null) {
                if (declaration instanceof DataDeclaration) {
                    holder.createInfoAnnotation(element, null).setTextAttributes(AgdaHighlighter.TYPE);
                }
                if (isConstructor(declaration)) {
                    holder.createInfoAnnotation(element, null).setTextAttributes(AgdaHighlighter.CONSTRUCTOR);
                }
            } else {
                holder.createErrorAnnotation(element, "Can't resolve");
            }

        }
    }

    private boolean hasAbsurdParent(PsiElement element) {
        if (element instanceof PsiFile) {
            return false;
        }
        return element instanceof AbsurdFunction || hasAbsurdParent(element.getParent());
    }


    private boolean isConstructor(PsiElement declaration) {
        if (declaration instanceof NameDeclaration) {
            PsiElement typeSignature = declaration.getParent();
            if (typeSignature instanceof TypeSignature) {
                if (typeSignature.getParent() instanceof Constructors) {
                    return true;
                }
            }
        }
        return false;
    }
}
