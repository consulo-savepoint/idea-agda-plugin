package org.jetbrains.agda.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeny.Kurbatsky
 */
public class CDataDeclaration extends CDeclaration {
    private String myName;
    private CExpression myType;
    private List<CTypeSignature> myConstructors = new ArrayList<CTypeSignature>();

    public CDataDeclaration(String name, CExpression type) {
        myName = name;
        myType = type;
    }

    public String getName() {
        return myName;
    }

    public CExpression getType() {
        return myType;
    }

    public List<CTypeSignature> geConstructors() {
        return myConstructors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("data ").append(myName).append(" : ").append(myType).append(" {\n");
        for (CTypeSignature cConstructor : myConstructors) {
            builder.append("\t").append(cConstructor).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
