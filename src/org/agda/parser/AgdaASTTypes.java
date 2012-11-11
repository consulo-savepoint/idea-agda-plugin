package org.agda.parser;

import com.intellij.psi.tree.IElementType;

public interface AgdaASTTypes {
    IElementType NAME = new AgdaElementType("NAME");
    IElementType QNAME = new AgdaElementType("QNAME");

    IElementType MODULE_DECLARATION = new AgdaElementType("MODULE_DECLARATION");
    IElementType IMPORT = new AgdaElementType("MODULE_DECLARATION");

    IElementType TYPE_REFERENCE = new AgdaElementType("TYPE_REFERENCE");
    IElementType CONSTRUCTOR_DECLARATION = new AgdaElementType("CONSTRUCTOR_DECLARATION");

    IElementType NAMED_ELEMENT = new AgdaElementType("NAMED_ELEMENT");
}
