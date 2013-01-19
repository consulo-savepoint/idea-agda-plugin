package org.jetbrains.agda.core

import java.util.ArrayList
import com.intellij.util.containers.hash.HashMap
import org.jetbrains.agda.core.expression.CMetaRef


class EquationsContainer {
    var lastVariable : Int = 0;
    val rules = ArrayList<Rule>()
    val variables = HashMap<String, CExpression>()
    val matavariables = HashMap<String, CExpression>()

    fun nextVarRef(name : String, aType: CExpression) : CMetaRef {
        lastVariable++;
        val varName = name + "_" + lastVariable
        matavariables.put(varName, aType)
        return CMetaRef(varName);

    }

}

class Rule(val name : String,  val value : CExpression) {

}