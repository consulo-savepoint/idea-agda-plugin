package org.agda;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.impl.source.tree.TreeElement;
import org.agda.parser.AgdaTokenTypes;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CodeAutoReplaceComponent implements ProjectComponent {
    private Map<String, String> mySubstitutionsRules = new HashMap<String, String>();
    private final Project myProject;

    public CodeAutoReplaceComponent(Project project) {
        this.myProject = project;
        mySubstitutionsRules.put("->", "→");
        mySubstitutionsRules.put("\\::", "∷");
        mySubstitutionsRules.put("\\bn", "ℕ");
        mySubstitutionsRules.put("\\all", "∀");
        mySubstitutionsRules.put("\\==", "≡");
    }

    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {
        PsiManager.getInstance(myProject).addPsiTreeChangeListener(new PsiTreeChangeAdapter() {
            @Override
            public void childAdded(@NotNull PsiTreeChangeEvent event) {
                super.childAdded(event);    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public void propertyChanged(@NotNull PsiTreeChangeEvent event) {
                event.getNewValue();
            }

            @Override
            public void childReplaced(@NotNull PsiTreeChangeEvent event) {
                PsiElement newChild = event.getNewChild();

                final ASTNode node = newChild.getNode();
                if (node instanceof LeafElement) {
                    final String replace = mySubstitutionsRules.get(newChild.getText());
                    if (replace != null) {
                        if (UndoManager.getInstance(myProject).isUndoInProgress()) {
                            return;
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                autoCorrect(replace, node);
                            }
                        });

                    }

                }
            }
        });
    }

    private void autoCorrect(final String replace, final ASTNode node) {
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CommandProcessor.getInstance().executeCommand(myProject, new Runnable() {
                    @Override
                    public void run() {
                        TreeElement newNode = new LeafPsiElement(AgdaTokenTypes.VALUE_CHARACTERS, replace);
                        node.getTreeParent().replaceChild(node, newNode);
                    }
                }, "Auto Rename", null);
            }
        });
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "AutoReplace";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
