package org.agda.highlight;

import com.intellij.openapi.vfs.VirtualFile;
import org.agda.ghci.AgdaCompilerMessage;
import org.agda.ghci.AgdaExernalAnnotation;
import org.agda.ghci.AgdaSyntaxAnnotation;

import java.util.List;

public class AnnotationResult {
    VirtualFile myFile;
    List<AgdaExernalAnnotation> myAnnotations;

    public AnnotationResult(VirtualFile file, List<AgdaExernalAnnotation> annotations) {
        myFile = file;
        myAnnotations = annotations;
    }
}
