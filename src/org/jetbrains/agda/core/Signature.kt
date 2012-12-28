package org.jetbrains.agda.core

import java.util.ArrayList
import com.intellij.util.containers.hash.HashMap


class Signature {
    var lastVariable : Int = 0;
    var rules = ArrayList<Rule>()
    val variables = HashMap<String, CExpression>()

    fun nextVarRef(aType: CExpression) : CRefExpression {
        lastVariable++;
        val varName = "_" + lastVariable
        variables.put(varName, aType)
        return CRefExpression(varName, varName);

    }

}

class Rule(val name : String,  val value : CExpression) {

}