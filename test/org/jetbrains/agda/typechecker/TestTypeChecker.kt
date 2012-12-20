package org.jetbrains.agda.typechecker

/**
 * @author Evgeny.Kurbatsky
 */
import org.junit.Test as test
import org.junit.Assert.*
import org.jetbrains.agda.core.Program
import org.jetbrains.agda.core.*

class TestTypeChecker {
    fun ref(name: String) = CRefExpression(name, name);
    fun CExpression.app(e2 : CExpression) = CApplication(this, e2)
    fun CExpression.arrow(e2 : CExpression) = CArrowExpression(this, e2)
    fun data(name: String, aType: CExpression) = CDataDeclaration(name, aType)

    val zero = ref("zero");
    fun succ(expr : CExpression) = CApplication(ref("succ"), expr)

    fun makeProgram() : Program<String> {
        val program = Program<String>();
        val natData = data("Nat", CSet())
        val zeroType = CTypeSignature("zero", ref("Nat"))
        val succ = CTypeSignature("succ", CArrowExpression(ref("Nat") , ref("Nat")))
        natData.getConstructors().add(zeroType);
        natData.getConstructors().add(succ);
        program.myDeclarations.put("Nat", natData)
        program.myDeclarations.put("zero", zeroType);
        program.myDeclarations.put("succ", succ);

        val listData = data("List", CSet() arrow CSet())
        val empty = CTypeSignature("empty", CImplicitArrowExpression("A", CSet(), ref("List") app ref("A")))
        val cons = CTypeSignature("cons", CImplicitArrowExpression("A", CSet(), CArrowExpression(ref("A") , CArrowExpression(ref("List") app ref("A"), ref("List") app ref("A")))))
        listData.getConstructors().add(empty)
        listData.getConstructors().add(cons)

        program.myDeclarations.put("List", listData)
        program.myDeclarations.put("empty", empty);
        program.myDeclarations.put("cons", cons);

        val vectorData = data("Vec", CSet() arrow (ref("Nat") arrow CSet()))
        val vect = ref("Vec")
        val emptyVector = CTypeSignature("empty", CImplicitArrowExpression("A", CSet(), vect app ref("A") app zero))
        val consVector = CTypeSignature("cons",
                CImplicitArrowExpression("A", CSet(),
                CImplicitArrowExpression("n", ref("Nat"),
                        (ref("A") arrow (
                            (vect app ref("A") app ref("n")) arrow (vect app ref("A") app (ref("succ") app ref("n"))))
                        )
                ))
        )
        vectorData.getConstructors().add(emptyVector)
        vectorData.getConstructors().add(consVector)

        program.myDeclarations.put("Vect", listData)
        program.myDeclarations.put("[]", emptyVector);
        program.myDeclarations.put("::", consVector);

        return program
    }

    test fun simpleTest() {
        val program = makeProgram();
        assertEquals(ref("Nat"), program.typeCheck(ref("zero")));
    }

    test fun applicationTest() {
        val program = makeProgram();
        assertEquals(ref("Nat"), program.typeCheck(succ(ref("zero"))));
    }

    test fun listTest() {
        val program = makeProgram();
        assertEquals(ref("List") app ref("Nat"), program.typeCheck(
                (ref("cons") app zero app ref("empty"))
        ));
    }

    test fun vectorTest() {
        val program = makeProgram();
        assertEquals(ref("Vect") app ref("Nat") app succ(ref("zero")), program.typeCheck(
                (ref("::") app succ(zero) app ref("[]"))
        ));
    }

}