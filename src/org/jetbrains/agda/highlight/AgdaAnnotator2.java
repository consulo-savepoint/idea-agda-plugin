package org.jetbrains.agda.highlight;


import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.psi.AName;
import org.jetbrains.agda.psi.DataDeclaration;
import org.jetbrains.annotations.NotNull;

public class AgdaAnnotator2 implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DataDeclaration) {
            DataDeclaration data = ((DataDeclaration)element);
            PsiElement id = data.getId();
            holder.createInfoAnnotation(id, null).setTextAttributes(AgdaHighlighter.TYPE);
        }
        if (element instanceof AName) {
            PsiElement root = element;
            while (!(root instanceof PsiFile)) {
                root = root.getParent();
            }
            String text = ((AName) element).getId().getText();
            PsiElement declaration = tryToFindDeclaration(root, text);
            if (declaration != null && (declaration instanceof DataDeclaration)) {
                holder.createInfoAnnotation(element, null).setTextAttributes(AgdaHighlighter.TYPE);
            }
        }
    }

    private PsiElement tryToFindDeclaration(PsiElement element, String text) {
        if (element instanceof DataDeclaration) {
            DataDeclaration dataDeclaration = ((DataDeclaration)element);
            if (text.equals(dataDeclaration.getId().getText())) {
                return dataDeclaration;
            }
        }
        for (PsiElement child: element.getChildren()) {
            PsiElement result = tryToFindDeclaration(child, text);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
