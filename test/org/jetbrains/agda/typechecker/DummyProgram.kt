package org.jetbrains.agda.typechecker

import org.jetbrains.agda.core.CSet
import org.jetbrains.agda.core.CRefExpression
import org.jetbrains.agda.core.CExpression
import org.jetbrains.agda.core.CApplication
import org.jetbrains.agda.core.CArrowExpression
import org.jetbrains.agda.core.CImplicitArrowExpression
import org.jetbrains.agda.core.CDataDeclaration
import org.jetbrains.agda.core.CTypeSignature
import org.jetbrains.agda.core.TypeChecker
import org.jetbrains.agda.core.CFunctionDeclaration
import com.intellij.util.containers.hash.HashMap
import org.jetbrains.agda.core.CDeclaration
import org.jetbrains.agda.core.CFunctionBody
import java.util.ArrayList


open class DummyProgram {
    fun ref(name: String) = CRefExpression(name, name);
    val Set = CSet(0)
    val emptyVector = ref("emptyVector");
    val emptyList = ref("emptyList");
    val Nat = ref("Nat");
    val zero = ref("zero");

    fun CExpression.app(e2 : CExpression) = CApplication(this, e2)
    fun CExpression.arrow(e2 : CExpression) = CArrowExpression(this, e2)

    fun data(name: String, aType: CExpression) = CDataDeclaration(name, aType)
    fun implisit(name: String, aType : CExpression, body: (CExpression) -> CExpression ) =
            CImplicitArrowExpression(name, aType, body(ref(name)))

    fun succ(expr : CExpression) = CApplication(ref("succ"), expr)

    fun List(expr : CExpression) = CApplication(ref("List"), expr)

    fun consL(fst : CExpression, snd : CExpression) = ref("consList") app fst app snd

    fun Vec(expr : CExpression, n : CExpression) = ref("Vec") app expr app n
    fun consV(fst : CExpression, snd : CExpression) = ref("consVector") app fst app snd


    fun makeProgram() : TypeChecker {
        val declarations = HashMap<String, CDeclaration>()
        fun CDataDeclaration.addConstructor(name : String, aType : CExpression) {
            val typeSignature = CTypeSignature(name, aType)
            this.getConstructors().add(typeSignature);
            declarations.put(name, typeSignature);
        }
        val program = TypeChecker({ reference ->
            declarations.get(reference);
        });

        val natData = data("Nat", Set)
        natData.addConstructor("zero", Nat)
        natData.addConstructor("succ", Nat arrow Nat)

        declarations.put("add", CFunctionDeclaration("add", Nat arrow (Nat arrow Nat),
                ArrayList<CFunctionBody>()
                ));

        val listData = data("List", Set arrow Set)
        listData.addConstructor("emptyList", implisit("A", Set) {A -> List(A)})
        listData.addConstructor("consList", implisit("A", Set) {A -> A arrow (List(A) arrow List(A))})

        declarations.put("List", listData)

        val vectorData = data("Vec", Set arrow (Nat arrow Set))
        vectorData.addConstructor("emptyVector", implisit("A", Set) {A -> Vec(A, zero)})
        vectorData.addConstructor("consVector",
                implisit("A", Set) {A -> implisit("n", Nat) {n ->
                    (A arrow (Vec(A, n) arrow Vec(A, succ(n))))
                }}
        )

        declarations.put("Vec", vectorData)

        return program
    }

}