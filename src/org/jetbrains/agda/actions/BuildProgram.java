package org.jetbrains.agda.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.core.Program;
import org.jetbrains.agda.core.ProgramBuilder;

public class BuildProgram extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        final Project project = e.getData(PlatformDataKeys.PROJECT);

        if (project == null || editor == null) {
            return;
        }
        final PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (file == null) {
            return;
        }
        Program program = new ProgramBuilder().build(file);
        program.printDebug();
    }

}
