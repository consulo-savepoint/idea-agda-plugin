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
        when (pairs) {
            is Nil<Pair<String, CExpression>> -> null
            is Cons<Pair<String, CExpression>> ->
               if (pairs.head.first == key)
                   pairs.head.second
               else
                   CContext(pairs.tail).get(key)
            else -> null
        }

    public fun put(name : String, cType : CExpression) : CContext =
        CContext(Cons(Pair(name, cType), pairs))


    public fun merge(context : CContext) : CContext =
        CContext(pairs.append(context.pairs))

}

val emptyContext = CContext(Nil())
