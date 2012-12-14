package org.jetbrains.agda.scope;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.mixfix.Grammar;
import org.jetbrains.agda.psi.*;

import java.util.Map;
import java.util.Set;

public class AgdaExpressionScope {
    private PsiElement myElement;

    public AgdaExpressionScope(PsiElement element) {
        myElement = element;
    }

    public Map<String, PsiElement> getVisibleDeclarations() {
        Map<String, PsiElement> declarations = AgdaGlobalScope.getDeclarations(myElement);
        PsiElement current = myElement;
        while (!(current instanceof PsiFile)) {
            if (current instanceof FunctionDeclaration) {
                FunctionDeclaration declaration = (FunctionDeclaration) current;
                addFunctionParameters(declaration.getExpressionList().get(0), declarations, Grammar.getOperationParts(declarations));
            }
            if (current instanceof TeleArrow) {
                for (Telescope telescope : ((TeleArrow) current).getTelescopeList()) {
                    TypeSignature typeSignature = null;
                    if (telescope instanceof ImplicitTelescope) {
                        typeSignature = ((ImplicitTelescope) telescope).getTypeSignature();
                    } else {
                        typeSignature = ((ExplicitTelescope) telescope).getTypeSignature();
                    }
                    for (PsiElement element : typeSignature.getNameDeclarationList()) {
                        declarations.put(element.getText(), element);
                    }
                }
            }
            if (current instanceof LambdaExpression) {
                LambdaExpression expression = (LambdaExpression)current;
                for (NameDeclaration nameDeclaration: expression.getNameDeclarationList()) {
                    declarations.put(nameDeclaration.getText(), nameDeclaration);
                }
            }
            if (current instanceof ForallExpression) {
                ForallExpression expression = (ForallExpression) current;
                for (TypedUntypedBinding typedUntypedBinding: expression.getTypedUntypedBindingList()) {
                    for (NameDeclaration nameDeclaration : typedUntypedBinding.getNameDeclarationList()) {
                        declarations.put(nameDeclaration.getText(), nameDeclaration);
                    }
                }
            }
            if (current instanceof DataDeclaration) {
                DataDeclaration dataDeclaration = (DataDeclaration) current;
                for (Binding binding : dataDeclaration.getBindingList()) {
                    for (NameDeclaration declaration : binding.getNameDeclarationList()) {
                        declarations.put(declaration.getText(), declaration);
                    }
                }
            }
            if (current instanceof LetExpression) {
                LetExpression letExpression = (LetExpression) current;
                NameDeclaration nameDeclaration = letExpression.getNameDeclaration();
                declarations.put(nameDeclaration.getText(), nameDeclaration);
            }
            current = current.getParent();
        }
        return declarations;
    }

    private void addFunctionParameters(PsiElement expression, Map<String, PsiElement> declarations, Set<String> operationParts) {
        if (expression instanceof AName) {
            String text = ((AName) expression).getText();
            if (! declarations.containsKey(text) && ! operationParts.contains(text)) {
                declarations.put(text, expression);
            }
        } else {
            for (PsiElement child : expression.getChildren()) {
                addFunctionParameters(child, declarations, operationParts);
            }
        }
    }

}
