package org.jetbrains.agda.mixfix;


import com.intellij.psi.PsiElement;
import org.jetbrains.agda.psi.Application;

import java.util.*;

public class Grammar {
    private Map<String, PsiElement> myDeclarations;
    private List<String> myOperatorParts = new ArrayList<String>();

    public Grammar(Map<String, PsiElement> declarations) {
        myDeclarations = declarations;
        for (String declaration: new HashSet<String>(myDeclarations.keySet())) {
            if (!declaration.contains("_")) {
                myDeclarations.remove(declaration);
            }
        }
        Set<String> operationParts = getOperationParts(myDeclarations);
        myOperatorParts.addAll(operationParts);
    }

    public static Set<String> getOperationParts(Map<String, PsiElement> declarations) {
        Set<String> operationParts = new HashSet<String>();

        for (String declaration: declarations.keySet()) {
            if (!declaration.contains("_")) {
                continue;
            }
            for (String str: decode(declaration)) {
                if (!str.equals("_")) {
                    operationParts.add(str);
                }
            }
        }
        return operationParts;
    }

    private static List<PsiElement> getListOfTerminals(Application application) {
        while (application.getParent() instanceof Application) {
           application = (Application)application.getParent();
        }
        List<PsiElement> result = new ArrayList<PsiElement>();
        PsiElement element = application;
        while (element instanceof Application) {
            PsiElement[] expressionList = element.getChildren();
            result.add(expressionList[0]);
            if (expressionList.length > 1) {
                element = expressionList[1];
            }  else {
                break;
            }
        }
        result.add(element);
        return result;
    }

    public static TreeElement parse(Application application) {
        List<PsiElement> listOfTerminals = getListOfTerminals(application);
        Map<String, PsiElement> declarations = org.jetbrains.agda.scope.namespace.getGlobalDeclarations(application);

        return new Grammar(declarations).parse(listOfTerminals);
    }

    private TreeElement parse(List<PsiElement> listOfTerminals) {
        List<TreeElement> list = new ArrayList<TreeElement>();
        for (PsiElement element : listOfTerminals) {
            list.add(new TreeElement(element, null));
        }
        TreeElement term = parseIterative(parseApplications(list));
        return term;
    }

    private List<TreeElement> parseApplications(List<TreeElement> list) {
        List<TreeElement> result = new ArrayList<TreeElement>();
        TreeElement current = null;
        for (TreeElement term : list) {
            if (myOperatorParts.contains(term.getText())) {
                if (current != null) {
                    result.add(current);
                    current = null;
                }
                result.add(term);
            } else {
                if (current == null) {
                    current = new TreeElement(null, null, term);
                } else {
                    current = new TreeElement(null, null, current, new TreeElement(null, null, term));
                }
            }
        }
        if (current != null) {
            result.add(current);
        }
        return result;
    }

    private TreeElement parseIterative(List<TreeElement> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        boolean good = true;
        for (TreeElement treeElement : list) {
            if (myOperatorParts.contains(treeElement.getText())) {
                good = false;
                break;
            }
        }
        if (good) {
            TreeElement current = null;
            for (TreeElement treeElement : list) {
                if (current == null) {
                    current = treeElement;
                } else {
                    current = new TreeElement(null, null, current, treeElement);
                }
            }
            return current;
        }
        for (int i = 0; i < list.size(); i++) {
            TreeElement term = applyRule(list, i);
            if (term != null) {
                return term;
            }
        }
        return null;
    }

    private TreeElement applyRule(List<TreeElement> list, int i) {
        for (String rule: myDeclarations.keySet()) {
            String[] ruleCode = decode(rule);
            int length = ruleCode.length - 1;
            if (i >= length) {
                boolean matches = true;
                for (int j = 0; j < ruleCode.length; j++) {
                    if (ruleCode[j].equals("_")) {
                        if(myOperatorParts.contains(list.get(i + j - length).getText())) {
                            matches = false;
                            break;
                        }
                    } else {
                        if(!ruleCode[j].equals(list.get(i + j - length).getText())) {
                            matches = false;
                            break;
                        }
                    }
                }

                if (matches) {
                    List<TreeElement> termList = list.subList(i - length, i + 1);
                    TreeElement term = new TreeElement(null, myDeclarations.get(rule), termList.toArray(new TreeElement[termList.size()]));

                    return parseIterative(replace(list, i, length, term));
                }
            }
        }
        return null;
    }

    private List<TreeElement> replace(List<TreeElement> list, int i, int size, TreeElement v) {
        ArrayList<TreeElement> terms = new ArrayList<TreeElement>(list);
        for (int j = 0; j <= size; j++) {
            terms.remove(i - size);
        }
        terms.add(i - size, v);
        return terms;
    }

    private static String[] decode(String rule) {
        if (rule.contains("_")) {
            List<String> result = new ArrayList<String>();
            int start = 0;
            for (int i = 0; i < rule.length(); i++) {
                if (rule.charAt(i) == '_') {
                    if (start < i) {
                        result.add(rule.substring(start, i));
                    }
                    result.add("_");
                    start = i + 1;
                } else {
                    if ((i + 1) == rule.length() ) {
                        result.add(rule.substring(start, i + 1));
                    }
                }
            }
            return result.toArray(new String[result.size()]);
        } else {
            return new String[]{rule};
        }
    }
}
