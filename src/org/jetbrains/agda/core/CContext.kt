package org.jetbrains.agda.core

import java.util.HashMap

/**
 * @author Evgeny.Kurbatsky
 */
public open class CContext(val map : Map<String, CExpression>) {

    public open fun put(name : String, cType : CExpression) : CContext {
        val newMap : HashMap<String, CExpression> = HashMap<String, CExpression>()
        newMap.putAll(map);
        newMap.put(name, cType)
        return CContext(newMap)
    }
}
