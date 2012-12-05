package org.jetbrains.agda.mixfix;


import com.intellij.psi.PsiElement;
import org.jetbrains.agda.psi.AName;
import org.jetbrains.agda.psi.Application;
import org.jetbrains.agda.scope.AgdaGlobalScope;

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

    public static Term parse(Application application) {
        List<PsiElement> listOfTerminals = getListOfTerminals(application);
        Map<String, PsiElement> declarations = AgdaGlobalScope.getDeclarations(application);

        return new Grammar(declarations).parse(listOfTerminals);
    }

    private Term parse(List<PsiElement> listOfTerminals) {
        List<Term> list = new ArrayList<Term>();
        for (PsiElement element : listOfTerminals) {
            if (element instanceof AName) {
                list.add(new Term(element.getText(), element, null));
            } else {
                list.add(new Term(null, element, null));
            }
        }
        Term term = parseIterative(parseApplications(list));
        return term;
    }

    private List<Term> parseApplications(List<Term> list) {
        List<Term> result = new ArrayList<Term>();
        Term current = null;
        for (Term term : list) {
            if (myOperatorParts.contains(term.getText())) {
                if (current != null) {
                    result.add(current);
                    current = null;
                }
                result.add(term);
            } else {
                if (current == null) {
                    current = new Term(null, null, null, term);
                } else {
                    current = new Term(null, null, null, current, new Term(null, null, null, term));
                }
            }
        }
        if (current != null) {
            result.add(current);
        }
        return result;
    }

    private Term parseIterative(List<Term> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        for (int i = 0; i < list.size(); i++) {
            Term term = applyRule(list, i);
            if (term != null) {
                return term;
            }
        }
        return null;
    }

    private Term applyRule(List<Term> list, int i) {
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
                    List<Term> termList = list.subList(i - length, i + 1);
                    Term term = new Term(null, null, myDeclarations.get(rule), termList.toArray(new Term[termList.size()]));

                    return parseIterative(replace(list, i, length, term));
                }
            }
        }
        return null;
    }

    private List<Term> replace(List<Term> list, int i, int size, Term v) {
        ArrayList<Term> terms = new ArrayList<Term>(list);
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
