package org.jetbrains.agda.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeny.Kurbatsky
 */
public class CFunctionDeclaration extends CDeclaration {
    private String myName;
    private CExpression myType;
    private List<FunctionBody> myBodes = new ArrayList<FunctionBody>();

    public CFunctionDeclaration(String name, CExpression type) {
        this.myName = name;
        this.myType = type;
    }

    public String getName() {
        return myName;
    }

    public CExpression getType() {
        return myType;
    }

    public List<FunctionBody> getBodyes() {
        return myBodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(myName).append(" : ").append(myType).append(" {\n");

        for (FunctionBody expression : myBodes) {
            builder.append("\t").append(expression.toString()).append("\n");
        }

        builder.append("}");
        return builder.toString();
    }
}
