package org.agda.ghci;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import org.agda.AgdaFileType;
import org.agda.compiler.AgdaCompiler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public final class GhciProjectComponent implements ProjectComponent {

    private final Project project;
    private final GHCIProcess process;

    public GhciProjectComponent(Project project) {
        this.project = project;
        process = new GHCIProcess();
    }

    public GHCIProcess getProcess() {
        return process;
    }

    public void projectOpened() {
    }

    public void projectClosed() {
    }

    @NotNull
    public String getComponentName() {
        return "GhciProjectComponent";
    }

    public void initComponent() {
        try {
            process.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disposeComponent() {
        process.stop();
    }
}
