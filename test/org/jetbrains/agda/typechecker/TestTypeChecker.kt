package org.jetbrains.agda.typechecker

/**
 * @author Evgeny.Kurbatsky
 */
import org.junit.Test as test
import org.junit.Assert.*
import org.jetbrains.agda.core.Program
import org.jetbrains.agda.core.*

class TestTypeChecker {
    fun makeProgram() : Program<String> {
        val program = Program<String>();
        val natData = data("Nat", CSet())
        val zero = CTypeSignature("zero", ref("Nat"))
        val succ = CTypeSignature("succ", CArrowExpression(ref("Nat") , ref("Nat")))
        natData.getConstructors().add(zero);
        natData.getConstructors().add(succ);
        program.myDeclarations.put("Nat", natData)
        program.myDeclarations.put("zero", zero);
        program.myDeclarations.put("succ", succ);

        val listData = data("List", CSet() app CSet())
        val empty = CTypeSignature("empty", CImplicitArrowExpression("A", CSet(), ref("List") app ref("A")))
        val cons = CTypeSignature("cons", CImplicitArrowExpression("A", CSet(), CArrowExpression(ref("A") , ref("List") app ref("A"))))
        listData.getConstructors().add(empty)
        listData.getConstructors().add(cons)

        program.myDeclarations.put("List", listData)
        program.myDeclarations.put("empty", empty);
        program.myDeclarations.put("cons", cons);

        return program
    }

    test fun simpleTest() {
        val program = makeProgram();
        assertEquals(ref("Nat"), program.typeCheck(ref("zero")));
    }

    test fun applicationTest() {
        val program = makeProgram();
        assertEquals(ref("Nat"), program.typeCheck(ref("succ") app ref("zero")));
    }

    test fun listTest() {
        val program = makeProgram();
        assertEquals(ref("List") app ref("Nat"), program.typeCheck(
                (ref("cons") app ref("zero")) app ref("empty")
        ));
    }

    fun ref(name: String) = CRefExpression(name, name);
    fun CExpression.app(e2 : CExpression) = CApplication(this, e2)
    fun data(name: String, aType: CExpression) = CDataDeclaration(name, aType)
}