package org.jetbrains.agda.core

import java.util.HashMap

/**
 * @author Evgeny.Kurbatsky
 */
public class CContext(val pair : Pair<String, CExpression>?, val parent : CContext?) {

    public fun get(key : String) : CExpression? =
        if (pair != null && pair.first == key)
            pair.second
        else
            parent?.get(key)

    public fun put(name : String, cType : CExpression) : CContext =
        CContext(Pair(name, cType), this)


    public fun merge(context : CContext) : CContext =
        CContext(pair, if (parent == null) context else parent.merge(context))

}

val emptyContext = CContext(null, null)
