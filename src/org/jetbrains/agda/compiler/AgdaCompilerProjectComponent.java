package org.jetbrains.agda.compiler;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import org.jetbrains.agda.AgdaFileType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;

public final class AgdaCompilerProjectComponent implements ProjectComponent {

    private final Project project;

    public AgdaCompilerProjectComponent(Project project) {
        this.project = project;
    }

    public void projectOpened() {
        CompilerManager manager = CompilerManager.getInstance(project);
        for (AgdaCompiler compiler : manager.getCompilers(AgdaCompiler.class)) {
            manager.removeCompiler(compiler);
        }
        HashSet<FileType> inputSet = new HashSet<FileType>(Arrays.asList(AgdaFileType.INSTANCE));
        HashSet<FileType> outputSet = new HashSet<FileType>();
        manager.addTranslatingCompiler(new AgdaCompiler(project), inputSet, outputSet);
    }

    public void projectClosed() {
    }

    @NotNull
    public String getComponentName() {
        return "AgdaCompilerComponent";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }
}
