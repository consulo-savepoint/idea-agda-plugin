package org.agda.ghci;


import com.intellij.openapi.util.Key;

import java.util.List;

public class AnnotationContainer {
    public static Key<AnnotationContainer> KEY = new Key<AnnotationContainer>("AnnotaitonContainer");
    public volatile List<AgdaExternalAnnotation> myAnnotations;

    public AnnotationContainer(List<AgdaExternalAnnotation> agdaExternalAnnotations) {
        myAnnotations = agdaExternalAnnotations;
    }
}
