package org.jetbrains.agda.parser.ast

import org.jetbrains.agda.lisp.SExpression
import java.util.ArrayList


abstract class ASTNode {

}

data class GenericNode(val name : String, val children : List<ASTNode>) : ASTNode()

data class Range(val intervals : List<Interval>) : ASTNode()

data class Interval(val start : Int, val end : Int) : ASTNode()

fun getASTNode(expression : SExpression) : ASTNode {
    val header = expression.getValue(0)!!
    if ("Range" == header) {
        val intervals = ArrayList<Interval>();
        for (child in expression.get(1)!!.getChildren()!!) {
            intervals.add(getASTNode(child) as Interval)
        }
        return Range(intervals)
    } else if ("Interval" == header) {
        val start = Integer.decode(expression.getChildren()!!.get(1).get(1))!!
        val end = Integer.decode(expression.getChildren()!!.get(2).get(1))!!
        return Interval(start, end)
    } else {
        val children = expression.getChildren()!!
        return GenericNode(header, children.subList(1, children.size()))
    }

}