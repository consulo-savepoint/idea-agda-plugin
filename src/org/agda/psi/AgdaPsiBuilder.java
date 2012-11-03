package org.agda.psi;


import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import org.agda.parser.AgdaASTType;

import java.util.ArrayList;
import java.util.List;

public class AgdaPsiBuilder {
    public static PsiElement build(ASTNode node) {
        /*
        if (node.getElementType() == AgdaASTType.MODULE_DECLARATION) {
            ASTNode current = node.getFirstChildNode();
            List<PsiElement> children = new ArrayList<PsiElement>();
            while (current != null) {
                children.add(build(current));
                current = current.getTreeNext();
            }

            return new AgdaModule(node, children.toArray(new PsiElement[children.size()]));
        } else {
        */
        if (" ".equals(node.getText())) {
            System.out.println("");
        }
        return new AgdaASTWrapper(node);
        //}
    }
}
