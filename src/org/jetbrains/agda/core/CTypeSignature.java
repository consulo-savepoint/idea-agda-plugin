package org.jetbrains.agda.core;

/**
 * @author Evgeny.Kurbatsky
 */
public class CTypeSignature extends CDeclaration {
    private String myName;

    public CTypeSignature(String name, CExpression type) {
        this.myName = name;
        this.myType = type;
    }



    @Override
    public String toString() {
        return myName + " : " + myType;
    }
}
