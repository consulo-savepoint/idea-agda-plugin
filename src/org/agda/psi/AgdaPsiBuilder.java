package org.agda.psi;


import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.agda.parser.AgdaASTTypes;

public class AgdaPsiBuilder {
    public static PsiElement build(ASTNode node) {
        if (node.getElementType() == AgdaASTTypes.MODULE_DECLARATION) {
            return new AgdaModule(node);
        } else if (node.getElementType() == AgdaASTTypes.IMPORT) {
            return new AgdaImport(node);
        } else {
            return new AgdaASTWrapper(node);
        }
    }
}
