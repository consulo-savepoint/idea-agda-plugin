package org.agda.highlight;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.agda.ghci.*;
import org.agda.psi.AgdaASTWrapper;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class AgdaAnnotator extends ExternalAnnotator<PsiFile, AnnotationResult> {

    @Override
    public PsiFile collectionInformation(@NotNull PsiFile file) {
        return file;
    }

    @Override
    public AnnotationResult doAnnotate(final PsiFile psiFile) {

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Document document = PsiDocumentManager.getInstance(psiFile.getProject()).getDocument(psiFile);
                    FileDocumentManager.getInstance().saveDocument(document);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        VirtualFile file = psiFile.getVirtualFile();
        if (file == null)
            return null;

        List<AgdaExternalAnnotation> agdaExternalAnnotations = LaunchAgda.load(file.getPath(), psiFile.getProject());

        if (agdaExternalAnnotations != null) {
            applyAnnotations(psiFile, agdaExternalAnnotations);
        }
        return new AnnotationResult(file, agdaExternalAnnotations);
    }

    public static void applyAnnotations(PsiFile file, List<AgdaExternalAnnotation> agdaExternalAnnotations) {
        for (AgdaExternalAnnotation annotation : agdaExternalAnnotations) {
            if (annotation instanceof AgdaSyntaxAnnotation) {
                PsiElement element = file.findElementAt(((AgdaSyntaxAnnotation) annotation).getStart());

                if (element != null) {
                    if (element instanceof AgdaASTWrapper) {
                        ((AgdaASTWrapper) element).isLoaded = true;
                    }
                    element.putUserData(AgdaSyntaxAnnotation.SYNTAX, (AgdaSyntaxAnnotation) annotation);
                }
            }
        }
    }

    @Override
    public void apply(@NotNull PsiFile file, AnnotationResult result, @NotNull AnnotationHolder holder) {
        if (result == null)
            return;

        List<Integer> goals = new ArrayList<Integer>();
        findGoals(file, goals);
        applySyntax(file, holder);
        if (result.myAnnotations != null) {
            for (AgdaExternalAnnotation annotation : result.myAnnotations) {

                if (annotation instanceof AgdaCompilerMessage) {
                    AgdaCompilerMessage message = (AgdaCompilerMessage) annotation;
                    holder.createErrorAnnotation(new TextRange(message.getStart(), message.getEnd()), message.getText());
                }
                if (annotation instanceof GoalAnnotation) {
                    GoalAnnotation goalAnnotation = (GoalAnnotation) annotation;
                    int index = goalAnnotation.getIndex();
                    if (index < goals.size()) {
                        Integer startOffset = goals.get(index);
                        holder.createWarningAnnotation(new TextRange(startOffset, startOffset + 2), goalAnnotation.getText());
                    }
                }
            }

        }
    }

    private void applySyntax(PsiElement element, AnnotationHolder holder) {
        PsiElement[] children = element.getChildren();
        if (children.length == 0) {
            AgdaSyntaxAnnotation annotation = element.getUserData(AgdaSyntaxAnnotation.SYNTAX);
            if (annotation != null) {
                if ("datatype".equals(annotation.getType())) {
                    Annotation infoAnnotation = holder.createInfoAnnotation(
                            new TextRange(annotation.getStart(), annotation.getEnd()), null);
                    infoAnnotation.setTextAttributes(AgdaHighlighter.TYPE);
                }
                if ("inductiveconstructor".equals(annotation.getType())) {
                    Annotation infoAnnotation = holder.createInfoAnnotation(
                            new TextRange(annotation.getStart(), annotation.getEnd()), null);
                    infoAnnotation.setTextAttributes(AgdaHighlighter.CONSTRUCTOR);
                }
            }
        } else {
            for (PsiElement child : children) {
                applySyntax(child, holder);
            }
        }
    }

    private void findGoals(PsiElement element, List<Integer> goals) {
        PsiElement[] children = element.getChildren();
        if (children.length == 0) {
            if (element.getText().equals("!!")) {
                goals.add(element.getTextOffset());
            }
        } else {
            for (PsiElement child : children) {
                findGoals(child, goals);
            }
        }
    }

}
