package org.jetbrains.agda.highlight;


import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.psi.AName;
import org.jetbrains.agda.psi.DataDeclaration;
import org.jetbrains.agda.psi.impl.ANameImpl;
import org.jetbrains.agda.scope.AgdaScope;
import org.jetbrains.annotations.NotNull;

public class AgdaAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DataDeclaration) {
            DataDeclaration data = ((DataDeclaration)element);
            PsiElement id = data.getId();
            holder.createInfoAnnotation(id, null).setTextAttributes(AgdaHighlighter.TYPE);
        }
        if (element instanceof ANameImpl) {
            PsiElement declaration = AgdaScope.findDeclaration((ANameImpl) element);
            if (declaration != null && (declaration instanceof DataDeclaration)) {
                holder.createInfoAnnotation(element, null).setTextAttributes(AgdaHighlighter.TYPE);
            }
        }
    }
}
