package org.jetbrains.agda.core

import java.util.ArrayList
import com.intellij.util.containers.hash.HashMap


class EquationsContainer {
    var lastVariable : Int = 0;
    val rules = ArrayList<Rule>()
    val variables = HashMap<String, CExpression>()
    val matavariables = HashMap<String, CExpression>()

    fun nextVarRef(name : String, aType: CExpression) : CRefExpression {
        lastVariable++;
        val varName = name + "_" + lastVariable
        matavariables.put(varName, aType)
        return CRefExpression(varName, varName);

    }

}

class Rule(val name : String,  val value : CExpression) {

}