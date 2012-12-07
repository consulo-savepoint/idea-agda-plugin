package org.jetbrains.agda.core;


import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.agda.mixfix.Grammar;
import org.jetbrains.agda.mixfix.TreeElement;
import org.jetbrains.agda.psi.*;
import org.jetbrains.agda.psi.impl.ANameImpl;
import org.jetbrains.agda.scope.AgdaGlobalScope;

import java.util.*;

public class Program {
    Map<PsiElement, CDeclaration> myDeclarations = new LinkedHashMap<PsiElement, CDeclaration>();
    Map<PsiElement, CExpression> myExpressions = new HashMap<PsiElement, CExpression>();
    CFunctionDeclaration myLassDeclaration = null;

    public static Program build(PsiElement element) {
        while (!(element instanceof PsiFile)) {
            element = element.getParent();
        }
        Program program = new Program();
        program.buildFromRoot(element);
        return program;
    }

    private void buildFromRoot(PsiElement element) {
        if (element instanceof DataDeclaration) {
            DataDeclaration psiData = (DataDeclaration) element;
            CExpression type = parseExpression(psiData.getExpression());
            CDataDeclaration cDataDeclaration = new CDataDeclaration(psiData.getNameDeclaration().getText(), type);
            for (TypeSignature typeSignature : psiData.getConstructors().getTypeSignatureList()) {
                CExpression cExpression = parseExpression(typeSignature.getExpression());
                for (NameDeclaration nameDeclaration : typeSignature.getNameDeclarationList()) {
                    CTypeSignature constructor = new CTypeSignature(nameDeclaration.getText(), cExpression);
                    cDataDeclaration.geConstructors().add(constructor);
                    myDeclarations.put(nameDeclaration, constructor);
                }
            }
            myDeclarations.put(psiData, cDataDeclaration);
        }
        if (element instanceof FunctionTypeDeclaration) {
            FunctionTypeDeclaration functionDeclaration = (FunctionTypeDeclaration) element;
            String name = functionDeclaration.getNameDeclaration().getText();
            myLassDeclaration = new CFunctionDeclaration(name, parseExpression(functionDeclaration.getExpression()));
            myDeclarations.put(functionDeclaration, myLassDeclaration);
        }
        if (element instanceof FunctionDeclaration) {
            FunctionDeclaration functionDeclaration = (FunctionDeclaration) element;
            Expression left = functionDeclaration.getExpressionList().get(0);
            Expression right = functionDeclaration.getExpressionList().get(1);

            CExpression leftPart = parseExpression(left);
            CExpression body = parseExpression(right);

            myLassDeclaration.getBodyes().add(new FunctionBody(leftPart, body));
        }
        if (element instanceof PsiFile) {
            for (PsiElement child : element.getChildren()) {
                buildFromRoot(child);
            }
        }
    }

    private CExpression parseExpression(PsiElement expression) {
        CExpression result = parseExpressionImpl(expression);
        myExpressions.put(expression, result);
        return result;
    }

    private CExpression parseExpressionImpl(PsiElement expression) {
        if (expression instanceof Application) {
            TreeElement treeElement = Grammar.parse((Application) expression);
            return treeToExpression(treeElement);
        }
        if (expression instanceof ANameImpl) {
            ANameImpl aName = (ANameImpl) expression;
            if (aName.getText().equals("Set")) {
                return new CSet();
            }
            PsiElement declaration = AgdaGlobalScope.findDeclaration(aName);
            if (declaration != null) {
                return new CRefExpression(declaration, aName.getText());
            }
        }
        if (expression instanceof FunctionType) {
            PsiElement[] children = expression.getChildren();
            CExpression left = parseExpression(children[0]);
            CExpression right = parseExpression(children[1]);
            return new ArrowExpression(left, right);
        }
        if (expression instanceof ParenthesisExpression) {
            return parseExpression(((ParenthesisExpression) expression).getExpression());
        }
        PsiElement firstChild = expression.getFirstChild();
        if (firstChild != null) {
            return parseExpression(firstChild);
        } else {
            return null;
        }

    }

    private CExpression treeToExpression(TreeElement treeElement) {
        if (treeElement.getDeclaration() != null) {
            PsiElement declaration = treeElement.getDeclaration();
            CRefExpression ref = new CRefExpression(declaration, getDeclarationName(declaration));
            List<CExpression> children = new ArrayList<CExpression>();
            for (TreeElement child: treeElement.getChildren()) {
                if (!child.isTerm()) {
                    children.add(treeToExpression(child));
                }
            }
            CExpression current = ref;
            for (CExpression child: children) {
                current = new CApplication(current, child);
            }
            return current;
        } else {
            if (treeElement.getElement() != null) {
                return parseExpression(treeElement.getElement());
            } else {
                TreeElement[] children = treeElement.getChildren();
                if (children.length == 1) {
                    return treeToExpression(children[0]);
                } else if (children.length == 2) {
                    return new CApplication(treeToExpression(children[0]), treeToExpression(children[1]));
                }
            }
        }
        return null;
    }

    private String getDeclarationName(PsiElement declaration) {
        if (declaration instanceof FunctionTypeDeclaration) {
            return ((FunctionTypeDeclaration) declaration).getNameDeclaration().getText();
        }
        if (declaration instanceof NameDeclaration) {
            return ((NameDeclaration) declaration).getText();
        }
        return null;
    }

    public CExpression getTypeOf(PsiElement elementAt) {
        CExpression expression = myExpressions.get(elementAt);
        if (expression != null) {
            return getTypeOf(expression);
        }
        return null;
    }

    private CExpression getTypeOf(CExpression expression) {
        if (expression instanceof CRefExpression) {
            PsiElement declaration = ((CRefExpression) expression).getDeclaration();
            CDeclaration cDeclaration = myDeclarations.get(declaration);
            if (cDeclaration != null) {
                return cDeclaration.getType();
            } else {
                return deduceType(myExpressions.get(declaration));
            }

        }
        if (expression instanceof CApplication) {
            CExpression typeOf = getTypeOf(((CApplication) expression).getLeft());
            if (typeOf instanceof ArrowExpression) {
                return ((ArrowExpression) typeOf).getRight();
            }
        }
        return null;
    }

    private CExpression deduceType(CExpression expression) {
        CExpression parent = expression.getParent();
        if (parent instanceof CApplication) {
            CApplication application = (CApplication) parent;
            if (application.getRight() == expression) {
                CExpression leftType = getTypeOf(application.getLeft());
                if (leftType instanceof ArrowExpression) {
                    return ((ArrowExpression) leftType).getLeft();
                }
                return null;
            }
        }
        return null;
    }

    public void printDebug() {
        for (CDeclaration declaration: myDeclarations.values()) {
            System.out.println(declaration.toString());
        }
    }
}
