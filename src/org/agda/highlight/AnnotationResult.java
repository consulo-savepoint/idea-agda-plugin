package org.agda.highlight;

import com.intellij.openapi.vfs.VirtualFile;
import org.agda.ghci.AgdaExternalAnnotation;
import org.agda.highlight.AgdaCompilerMessage;

import java.util.List;

public class AnnotationResult {

    VirtualFile myFile;
    List<AgdaExternalAnnotation> myAnnotations;
    List<AgdaCompilerMessage> myMessages;

    public AnnotationResult(VirtualFile file, List<AgdaExternalAnnotation> messages, List<AgdaCompilerMessage> list) {
        myFile = file;
        myAnnotations = messages;
        myMessages = list;
    }
}
