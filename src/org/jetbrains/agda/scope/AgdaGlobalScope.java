package org.jetbrains.agda.scope;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.mixfix.Grammar;
import org.jetbrains.agda.mixfix.TreeElement;
import org.jetbrains.agda.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AgdaGlobalScope {
    public static PsiElement findDeclaration(@NotNull AgdaReferenceElementImpl element) {
        if (element.getParent() instanceof Application) {
            TreeElement term = Grammar.parse((Application) element.getParent());
            if (term != null) {
                PsiElement declaration = term.getDeclaration(element);
                if (declaration != null) {
                    return declaration;
                }
            }
        }
        Map<String, PsiElement> declarations = new AgdaExpressionScope(element).getVisibleDeclarations();
        String text = ((AName) element).getId().getText();
        return declarations.get(text);
    }

    private static void getDeclarations(PsiElement element, Map<String, PsiElement> map) {
        if (element instanceof DataDeclaration) {
            DataDeclaration dataDeclaration = ((DataDeclaration) element);
            map.put(dataDeclaration.getNameDeclaration().getText(), element);
        }
        if (element instanceof Constructors) {
            Constructors constructors = ((Constructors) element);
            for (TypeSignature constructor: constructors.getTypeSignatureList()) {
                for (NameDeclaration nameDeclaration: constructor.getNameDeclarationList()) {
                    map.put(nameDeclaration.getText(), nameDeclaration);
                }
            }
        }
        if (element instanceof FunctionTypeDeclaration) {
            String text = ((FunctionTypeDeclaration) element).getFirstChild().getText();
            map.put(text, element);
        }
        for (PsiElement child : element.getChildren()) {
            getDeclarations(child, map);
        }
    }

    public static Map<String, PsiElement> getDeclarations(PsiElement element) {
        PsiElement root = element;
        while (!(root instanceof PsiFile)) {
            root = root.getParent();
        }
        Map<String, PsiElement> map = new HashMap<String, PsiElement>();
        getDeclarations(root, map);
        return map;
    }
}
