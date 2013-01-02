package org.jetbrains.agda.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;

import java.util.HashSet;
import java.util.Set;


public class AgdaCompletionContributor extends CompletionContributor {

    @Override
    public void fillCompletionVariants(final CompletionParameters parameters, final CompletionResultSet result) {
        if (parameters.getCompletionType() == CompletionType.BASIC) {
            final Set<String> values = new HashSet<String>();
            findCompletion(parameters.getOriginalFile(), values);
            for (String value : values) {
                result.addElement(LookupElementBuilder.create(value));
            }
        }
    }

    private void findCompletion(PsiElement element, Set<String> completions) {

    }
}