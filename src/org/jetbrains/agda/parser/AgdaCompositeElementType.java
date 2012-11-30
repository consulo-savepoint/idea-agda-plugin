package org.jetbrains.agda.parser;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.agda.AgdaLanguage;
import org.jetbrains.annotations.NonNls;

/**
 * @author Evgeny.Kurbatsky
 */
public class AgdaCompositeElementType extends IElementType {
    public AgdaCompositeElementType(@NonNls String debugName) {
        super(debugName, AgdaLanguage.INSTANCE);
    }
}
