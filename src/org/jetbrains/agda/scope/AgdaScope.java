package org.jetbrains.agda.scope;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.mixfix.Grammar;
import org.jetbrains.agda.mixfix.Term;
import org.jetbrains.agda.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AgdaScope {
    public static PsiElement findDeclaration(@NotNull AgdaReferenceElementImpl element) {
        if (element.getParent() instanceof Application) {
            Term term = Grammar.parse((Application) element.getParent());
            if (term != null) {
                return term.getDeclaration(element);
            }
        }
        Map<String, PsiElement> declarations = getDeclarations(element);
        String text = ((AName) element).getId().getText();
        return declarations.get(text);
    }

    private static void getDeclarations(PsiElement element, Map<String, PsiElement> map) {
        if (element instanceof DataDeclaration) {
            DataDeclaration dataDeclaration = ((DataDeclaration) element);
            map.put(dataDeclaration.getId().getText(), element);
        }
        if (element instanceof Constructors) {
            Constructors constructors = ((Constructors) element);
            for (TypeSignature constructor: constructors.getTypeSignatureList()) {
                Ids id =  constructor.getIds();
                map.put(id.getText(), constructor);
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
