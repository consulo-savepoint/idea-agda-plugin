package org.agda.highlight;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.agda.ghci.AgdaExternalAnnotation;
import org.agda.ghci.AnnotationContainer;
import org.agda.ghci.LaunchAgda;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
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
        List<AgdaCompilerMessage> messages = new ArrayList<AgdaCompilerMessage>();
        List<AgdaExternalAnnotation> agdaExternalAnnotations = LaunchAgda.load(file.getPath(), messages);
        if (agdaExternalAnnotations != null) {
            AnnotationContainer.INSTANCE.myAnnotations = agdaExternalAnnotations;
        }
        return new AnnotationResult(file, agdaExternalAnnotations, messages);
    }

    @Override
    public void apply(@NotNull PsiFile file, AnnotationResult result, @NotNull AnnotationHolder holder) {
        if (result == null)
            return;
        if (result.myAnnotations != null) {
            for (AgdaExternalAnnotation annotation: result.myAnnotations) {
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

        }

        showMessages(file, holder, result.myFile, result.myMessages);
    }

    private static void showMessages(PsiFile psiFile, AnnotationHolder annotationHolder, VirtualFile file, List<AgdaCompilerMessage> ghcMessages) {
        File mainFile = new File(file.getPath());
        for (AgdaCompilerMessage agdaMessage : ghcMessages) {
            annotationHolder.createErrorAnnotation(new TextRange(agdaMessage.getStart(), agdaMessage.getEnd()), agdaMessage.getText());
        }
    }
}
