package org.jetbrains.agda.parser

import com.intellij.lang.PsiBuilder
import org.jetbrains.agda.agdaInterface.AgdaInteface
import com.intellij.psi.tree.IElementType
import org.jetbrains.agda.lisp.LispParser
import org.jetbrains.agda.lisp.SExpression
import java.util.ArrayList
import org.jetbrains.agda.parser.ast.getASTNode

public class AgdaParsing(val builder : PsiBuilder) {
    var UNKNOWN : IElementType = AgdaToken("UNKNOWN")
    var NAME : IElementType = AgdaToken("NAME")
    var MODULE : IElementType = AgdaToken("MODULE")


    fun parseFile() : Unit {
        val result = AgdaInteface().run(builder.getOriginalText()?.toString())
        System.out.println(result)
        val parser : LispParser = LispParser(result)
        val sexpression : SExpression = parser.parseExpression()
        val astNode = getASTNode(sexpression)
        var module : SExpression = sexpression.get(1)!!.get(1)!!.get(0)!!
        parseModule(module)
        while (!builder.eof()) {
            val mark = builder.mark()!!
            val currentOffset : Int = builder.getCurrentOffset()
            builder.advanceLexer()
            val traverse : IElementType? = traverse(sexpression, currentOffset)
            if (traverse != null) {
                mark.done(traverse)
            } else {
                mark.done(UNKNOWN)
            }
        }
    }

    fun parseBody(sexpression : SExpression) {

        while (!builder.eof()) {
            val mark = builder.mark()!!
            val currentOffset : Int = builder.getCurrentOffset()
            builder.advanceLexer()
            val traverse : IElementType? = traverse(sexpression, currentOffset)
            if (traverse != null) {
                mark.done(traverse)
            } else {
                mark.done(UNKNOWN)
            }
        }

    }

    private fun parseModule(module : SExpression) : Unit {
        var mark : PsiBuilder.Marker = builder.mark()!!
        val rangeList = parseRange(module.get(1)!!)

        parseBody(module);

        mark.done(MODULE)
    }

    private fun parseRange(expression : SExpression) : List<Int> {
        assert("Range".equals(expression.getValue(0)))
        val list = ArrayList<Int>()

        for (child in expression.get(1)!!.getChildren()!!) {
            var index : Int = Integer.decode(child.get(1)!!.getValue(1))!!
            list.add(index)
        }

        return list
    }

    private fun traverse(sexpression : SExpression, currentOffset : Int) : IElementType? {
        if (!sexpression.isAtom()) {
            if ((sexpression.getChildren()?.size())!! > 0 &&
                    (sexpression.get(0)?.isAtom())!! &&
                    (sexpression.getValue(0)?.equals("Name"))!!) {
                val interval = sexpression.get(1)?.get(1)?.get(0)!!
                val indexString : String? = interval.get(1)?.get(1)?.getValue()
                if (indexString != null) {
                    var index : Int = Integer.decode(indexString)!!
                    if (index == currentOffset + 1) {
                        return NAME
                    }
                }
            }

            for (e in sexpression.getChildren()!!) {
                val t : IElementType? = traverse(e, currentOffset)
                if (t != null) {
                    return t
                }

            }
        }

        return null;
    }

}
