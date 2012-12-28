package org.jetbrains.agda.core

import java.util.HashMap

/**
 * @author Evgeny.Kurbatsky
 */
public class CContext(val map : Map<String, CExpression>) {

    public fun put(name : String, cType : CExpression) : CContext {
        val newMap : HashMap<String, CExpression> = HashMap<String, CExpression>()
        newMap.putAll(map);
        newMap.put(name, cType)
        return CContext(newMap)
    }

    public fun merge(context : CContext) : CContext {
        val newMap : HashMap<String, CExpression> = HashMap<String, CExpression>()
        newMap.putAll(map);
        newMap.putAll(context.map)
        return CContext(newMap)
    }
}

fun emptyContext() = CContext(HashMap<String, CExpression>())
