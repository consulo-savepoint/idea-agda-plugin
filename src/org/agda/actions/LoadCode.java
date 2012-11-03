package org.agda.actions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.agda.ghci.AgdaSyntaxAnnotation;
import org.agda.ghci.LaunchAgda;

import java.io.File;
import java.util.List;

public class LoadCode extends AnAction {

    public LoadCode() {
        super("Load Code");
    }



    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        VirtualFile virtualFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        String path = virtualFile.getPath();
    }

}