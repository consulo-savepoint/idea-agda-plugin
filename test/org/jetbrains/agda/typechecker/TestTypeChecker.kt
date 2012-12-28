package org.jetbrains.agda.typechecker

/**
 * @author Evgeny.Kurbatsky
 */
import org.junit.Test as test
import org.junit.Assert.*
import org.jetbrains.agda.core.Program
import org.jetbrains.agda.core.*

class TestTypeChecker {
    val Set = CSet()
    fun ref(name: String) = CRefExpression(name, name);
    fun CExpression.app(e2 : CExpression) = CApplication(this, e2)
    fun CExpression.arrow(e2 : CExpression) = CArrowExpression(this, e2)

    fun CExpression.hasType(aType : CExpression) {
        val program = makeProgram();
        assertEquals(aType, program.calculateType(this));
    }
    fun data(name: String, aType: CExpression) = CDataDeclaration(name, aType)
    fun implisit(name: String, aType : CExpression, body: (CExpression) -> CExpression ) =
        CImplicitArrowExpression(name, aType, body(ref(name)))



    val Nat = ref("Nat");
    val zero = ref("zero");
    fun succ(expr : CExpression) = CApplication(ref("succ"), expr)

    fun List(expr : CExpression) = CApplication(ref("List"), expr)
    val emptyL = ref("emptyL");
    fun consL(fst : CExpression, snd : CExpression) = ref("cons") app fst app snd

    fun Vec(expr : CExpression, n : CExpression) = ref("Vec") app expr app n
    val emptyV = ref("emptyV");
    fun consV(fst : CExpression, snd : CExpression) = ref("consV") app fst app snd


    fun makeProgram() : Program<String> {
        val program = Program<String>();
        val natData = data("Nat", CSet())
        val zeroType = CTypeSignature("zero", Nat)
        val succType = CTypeSignature("succ", Nat arrow Nat)

        natData.getConstructors().add(zeroType);
        natData.getConstructors().add(succType);
        program.myDeclarations.put("Nat", natData)
        program.myDeclarations.put("zero", zeroType);
        program.myDeclarations.put("succ", succType);

        val listData = data("List", Set arrow Set)
        val empty = CTypeSignature("emptyL", implisit("A", Set) {A -> List(A)})
        val cons = CTypeSignature("cons", implisit("A", Set) {A -> A arrow (List(A) arrow List(A))})
        listData.getConstructors().add(empty)
        listData.getConstructors().add(cons)

        program.myDeclarations.put("List", listData)
        program.myDeclarations.put("emptyL", empty);
        program.myDeclarations.put("cons", cons);

        val vectorData = data("Vec", Set arrow (Nat arrow Set))
        val emptyVector = CTypeSignature("emptyV", implisit("A", Set) {A -> Vec(A, zero)})
        val consVector = CTypeSignature("consV",
                implisit("A", Set) {A -> implisit("n", Nat) {n ->
                        (A arrow (Vec(A, n) arrow Vec(A, succ(n))))
                }}
        )
        vectorData.getConstructors().add(emptyVector)
        vectorData.getConstructors().add(consVector)

        program.myDeclarations.put("Vec", listData)
        program.myDeclarations.put("emptyV", emptyVector);
        program.myDeclarations.put("consV", consVector);

        return program
    }



    test fun simpleTest() {
        zero hasType Nat
    }

    test fun applicationTest() {
        succ(zero) hasType Nat
    }

    test fun listTest() {
       consL(zero, emptyL) hasType List(Nat)
    }

    test fun vectorTest() {
        consV(succ(zero), emptyV) hasType Vec(Nat, succ(zero))
    }

}