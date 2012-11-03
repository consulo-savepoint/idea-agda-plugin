package org.agda.ghci;


import java.util.List;

public class AnnotationContainer {
    public static AnnotationContainer INSTANCE = new AnnotationContainer();
    public volatile List<AgdaExernalAnnotation> myAnnotations;
}
