package org.jetbrains.agda.core

import java.util.HashMap
import org.jetbrains.agda.util.FList
import org.jetbrains.agda.util.Cons
import org.jetbrains.agda.util.Nil

/**
 * @author Evgeny.Kurbatsky
 */

public class CContext(val pairs : FList<Pair<String, CExpression>>) {

    public fun get(key : String) : CExpression? =
        pairs.fold<CExpression?>(null) { pair, next ->
            if (pair.first == key) pair.second else next()
        }

    public fun put(name : String, cType : CExpression) : CContext =
        CContext(Cons(Pair(name, cType), pairs))


    public fun merge(context : CContext) : CContext =
        CContext(pairs.append(context.pairs))

}

val emptyContext = CContext(Nil())
