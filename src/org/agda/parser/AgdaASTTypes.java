package org.agda.parser;

import com.intellij.psi.tree.IElementType;
import org.agda.psi.AgdaBinding;
import org.agda.psi.AgdaImport;
import org.agda.psi.AgdaModule;

public interface AgdaASTTypes {
    IElementType IMPORT_NAME = new AgdaElementType("IMPORT_NAME", null);
    IElementType QNAME = new AgdaElementType("QNAME", null);

    IElementType MODULE_DECLARATION = new AgdaElementType("MODULE_DECLARATION", AgdaModule.class);
    IElementType IMPORT = new AgdaElementType("IMPORT", AgdaImport.class);
    IElementType RENAMING = new AgdaElementType("RENAMING", null);

    // Expressions
    IElementType REFERENCE = new AgdaElementType("REFERENCE", null);
    IElementType APPLICATION = new AgdaElementType("APPLICATION", null);

    IElementType TYPE_DECLARATION = new AgdaElementType("TYPE_DECLARATION", null);
    IElementType CONSTRUCTOR_DECLARATION = new AgdaElementType("CONSTRUCTOR_DECLARATION", null);
    IElementType BINDINGS = new AgdaElementType("BINDINGS", AgdaBinding.class);

    IElementType OTHER_ELEMENT = new AgdaElementType("OTHER_ELEMENT", null);


}
