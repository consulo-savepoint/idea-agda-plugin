package org.jetbrains.agda.core

import java.util.HashMap

/**
 * @author Evgeny.Kurbatsky
 */
public open class CContext(val map : Map<String, CExpression>) {

    public open fun put(name : String, cType : CExpression) : CContext {
        var map : HashMap<String, CExpression> = HashMap<String, CExpression>()
        map.put(name, cType)
        return CContext(map)
    }
}
