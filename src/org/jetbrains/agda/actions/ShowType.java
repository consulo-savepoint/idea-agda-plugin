package org.jetbrains.agda.actions;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.hint.HintManagerImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.LightweightHint;
import org.jetbrains.agda.core.CExpression;
import org.jetbrains.agda.core.Program;
import org.jetbrains.agda.core.ProgramBuilder;

import javax.swing.*;

public class ShowType extends AnAction {
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
        PsiElement elementAt = findElementAt(file, editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd());
        if (elementAt != null) {
            Program program = new ProgramBuilder().build(file);
            CExpression typeOf = program.getTypeOf(elementAt);
            if (typeOf != null) {
                JLabel component = new JLabel(typeOf.toString());

                final LightweightHint hint = new LightweightHint(component);
                final HintManagerImpl hintManager = HintManagerImpl.getInstanceImpl();
                hintManager.showEditorHint(hint, editor, hintManager.getHintPosition(hint, editor, HintManager.ABOVE),
                        HintManager.HIDE_BY_ANY_KEY | HintManager.HIDE_BY_LOOKUP_ITEM_CHANGE | HintManager.UPDATE_BY_SCROLLING,
                        0, false, HintManager.ABOVE);
            }
        }
    }

    private PsiElement findElementAt(PsiElement element, int selectionStart, int selectionEnd) {
        TextRange textRange = element.getTextRange();
        if (textRange.getStartOffset() >= selectionStart && textRange.getEndOffset() <= selectionEnd) {
            return element;
        }

        for (PsiElement child : element.getChildren()) {
            if (child.getTextRange().getStartOffset() <= selectionStart && child.getTextRange().getEndOffset() >= selectionEnd) {
                return findElementAt(child, selectionStart, selectionEnd);
            }
        }

        return null;
    }
}
