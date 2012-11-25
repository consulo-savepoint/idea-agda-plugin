package org.agda.parser;

import com.intellij.psi.tree.IElementType;

public interface AgdaASTTypes {
    IElementType NAME = new AgdaElementType("NAME");
    IElementType QNAME = new AgdaElementType("QNAME");

    IElementType MODULE_DECLARATION = new AgdaElementType("MODULE_DECLARATION");
    IElementType IMPORT = new AgdaElementType("IMPORT");
    IElementType RENAMING = new AgdaElementType("RENAMING");

    // Expressions
    IElementType REFERENCE = new AgdaElementType("REFERENCE");
    IElementType APPLICATION = new AgdaElementType("APPLICATION");

    IElementType TYPE_DECLARATION = new AgdaElementType("TYPE_DECLARATION");
    IElementType CONSTRUCTOR_DECLARATION = new AgdaElementType("CONSTRUCTOR_DECLARATION");
    IElementType BINDINGS = new AgdaElementType("BINDINGS");

    IElementType NAMED_ELEMENT = new AgdaElementType("NAMED_ELEMENT");


}
