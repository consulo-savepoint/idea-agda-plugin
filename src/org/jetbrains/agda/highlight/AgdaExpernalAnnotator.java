package org.jetbrains.agda.highlight;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jetbrains.agda.external.*;
import org.jetbrains.agda.psi.GoalExpression;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class AgdaExpernalAnnotator extends ExternalAnnotator<PsiFile, AnnotationResult> {

    @Override
    public PsiFile collectionInformation(@NotNull PsiFile file) {
        return file;
    }

    @Override
    public AnnotationResult doAnnotate(final PsiFile psiFile) {
        VirtualFile file = psiFile.getVirtualFile();
        if (file == null)
            return null;

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    ApplicationManager.getApplication().runWriteAction(new Runnable() {
                        @Override
                        public void run() {
                            Document document = PsiDocumentManager.getInstance(psiFile.getProject()).getDocument(psiFile);
                            FileDocumentManager.getInstance().saveDocument(document);
                        }
                    });
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Annotations");
        AgdaProjectComponent component = psiFile.getProject().getComponent(AgdaProjectComponent.class);
        List<AgdaExternalAnnotation> agdaExternalAnnotations = component.load(file.getPath());

        return new AnnotationResult(file, agdaExternalAnnotations);
    }

    public static void applyAnnotations(final PsiFile file, List<AgdaExternalAnnotation> agdaExternalAnnotations) {
        for (AgdaExternalAnnotation annotation : agdaExternalAnnotations) {
            if (annotation instanceof AgdaSyntaxAnnotation) {
                AgdaSyntaxAnnotation syntaxAnnotation = (AgdaSyntaxAnnotation) annotation;
                PsiElement element = file.findElementAt(syntaxAnnotation.getStart());

                if (element != null) {
                    while (!(element instanceof ASTWrapperPsiElement)) {
                        element = element.getParent();
                        if (element instanceof PsiFile) {
                            break;
                        }
                    }
                    if (!(element instanceof ASTWrapperPsiElement)) {
                      continue;
                    }

                    /*
                    if (syntaxAnnotation.getReference() != null) {
                        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(new File(syntaxAnnotation.getReference()));
                        PsiFile file1 = PsiManager.getInstance(file.getProject()).findFile(virtualFile);

                        final PsiElement elementAt = file1.findElementAt(syntaxAnnotation.getReferenceIndex());
                        if (elementAt != null) {
                            astWrapper.myReference = elementAt;
                        }
                    }
                    */
                }
            }
        }
    }

    @Override
    public void apply(@NotNull PsiFile file, AnnotationResult result, @NotNull AnnotationHolder holder) {
        if (result == null)
            return;

        if (result.myAnnotations != null) {
            applyAnnotations(file, result.myAnnotations);
        }

        List<GoalExpression> goals = new ArrayList<GoalExpression>();
        findGoals(file, goals);

        //applySyntax(file, holder);

        if (result.myAnnotations != null) {
            for (AgdaExternalAnnotation annotation : result.myAnnotations) {

                if (annotation instanceof AgdaCompilerMessage) {
                    Document document = PsiDocumentManager.getInstance(file.getProject()).getDocument(file);
                    AgdaCompilerMessage message = (AgdaCompilerMessage) annotation;
                    if (message.isValid()) {
                        holder.createErrorAnnotation(new TextRange(message.getStart(document), message.getEnd(document)), message.getText());
                    }
                }
                if (annotation instanceof GoalAnnotation) {
                    GoalAnnotation goalAnnotation = (GoalAnnotation) annotation;
                    int index = goalAnnotation.getIndex();
                    if (index < goals.size()) {
                        GoalExpression expression = goals.get(index);
                        holder.createWarningAnnotation(expression, goalAnnotation.getText());
                    }
                }
            }

        }
    }

    private void applySyntax(PsiElement element, AnnotationHolder holder) {
        PsiElement[] children = element.getChildren();
        if (children.length == 0) {

        } else {
            for (PsiElement child : children) {
                applySyntax(child, holder);
            }
        }
    }

    private void findGoals(PsiElement element, List<GoalExpression> goals) {
        PsiElement[] children = element.getChildren();
        if (element instanceof GoalExpression) {
            goals.add((GoalExpression)element);
        }
        for (PsiElement child : children) {
            findGoals(child, goals);
        }
    }

}
