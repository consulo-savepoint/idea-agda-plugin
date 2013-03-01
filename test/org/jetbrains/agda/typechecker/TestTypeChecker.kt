package org.jetbrains.agda.typechecker

/**
 * @author Evgeny.Kurbatsky
 */
import org.junit.Test as test
import org.junit.Assert.*
import org.jetbrains.agda.core.TypeChecker
import org.jetbrains.agda.core.*
import java.util.HashMap

class TestTypeChecker : DummyProgram() {
    fun CExpression.hasType(aType : CExpression) {
        val program = makeProgram();
        assertEquals(aType, program.calculateType(this));
    }

    test fun simpleTest() {
        zero hasType Nat
    }

    test fun applicationTest() {
        succ(zero) hasType Nat
    }

    test fun listTest() {
       consL(zero, emptyList) hasType List(Nat)
    }

    test fun vectorTest() {
        consV(succ(zero), emptyVector) hasType Vec(Nat, succ(zero))
    }

}