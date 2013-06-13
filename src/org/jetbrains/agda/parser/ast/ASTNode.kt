package org.jetbrains.agda.parser.ast

import org.jetbrains.agda.lisp.SExpression
import java.util.ArrayList


abstract class ASTNode {

}

data class ListNode(val children : List<ASTNode>) : ASTNode()

data class IdNode(val name : String) : ASTNode()

data class GenericNode(val name : String, val children : List<ASTNode>) : ASTNode()

data class Range(val intervals : List<Interval>) : ASTNode()

data class Interval(val start : Int, val end : Int) : ASTNode()

fun getASTNode(expression : SExpression) : ASTNode {
    val header = expression.get(0)?.getValue()
    if (header != null) {
        if ("Range" == header) {
            val intervals = ArrayList<Interval>();
            for (child in expression.get(1)!!.getChildren()!!) {
                intervals.add(getASTNode(child) as Interval)
            }
            return Range(intervals)
        } else if ("Interval" == header) {
            val start = Integer.decode(expression.getChildren()!!.get(1).getValue(1)!!)!!
            val end = Integer.decode(expression.getChildren()!!.get(2).getValue(1)!!)!!
            return Interval(start, end)
        } else if ("Id" == header) {
            return IdNode(expression.getValue(1)!!)
        } else {
            val children = expression.getChildren()!!
            return GenericNode(header, children.subList(1, children.size()).map { getASTNode(it) })
        }
    } else {
        return ListNode(expression.getChildren()!!.map { getASTNode(it) })
    }

}