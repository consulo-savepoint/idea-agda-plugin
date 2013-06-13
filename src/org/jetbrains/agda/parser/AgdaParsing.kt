package org.jetbrains.agda.parser

import com.intellij.lang.PsiBuilder
import org.jetbrains.agda.agdaInterface.AgdaInteface
import com.intellij.psi.tree.IElementType
import org.jetbrains.agda.lisp.LispParser
import org.jetbrains.agda.lisp.SExpression
import java.util.ArrayList

public class AgdaParsing(val builder : PsiBuilder) {
    var UNKNOWN : IElementType = AgdaToken("UNKNOWN")
    var NAME : IElementType = AgdaToken("NAME")
    var MODULE : IElementType = AgdaToken("MODULE")


    fun parseFile() : Unit {
        val result = AgdaInteface().run(builder.getOriginalText()?.toString())
        System.out.println(result)
        val parser : LispParser = LispParser(result)
        val sexpression : SExpression = parser.parseExpression().get(1)!!.get(1)!!.get(0)!!;

        parseNode(sexpression)

        while (!builder.eof()) {
            val mark = builder.mark()!!
            val currentOffset : Int = builder.getCurrentOffset()
            builder.advanceLexer()

            mark.done(UNKNOWN)
        }
    }

    private fun getNodeType(expression: SExpression) : IElementType? {
        if (expression.getValue(0) == "Module") {
            return MODULE;
        }
        return null;
    }

    private fun getOffsetsList(expression: SExpression) : List<Int> {
        val range = expression.get(1)!!
        assert("Range" == range.getValue(0))
        val intervals = range.get(1)!!.getChildren()!!
        val result = ArrayList<Int>()
        for (interval in intervals) {
            result.add(Integer.decode(interval.get(1)!!.getValue(1)!!)!! - 1)
        }
        return result;
    }

    private fun parseNode(expression: SExpression) : Unit {
        var mark : PsiBuilder.Marker = builder.mark()!!

        val nodeType = getNodeType(expression)
        val offsetsList = getOffsetsList(expression)
        if (nodeType != null) {
            while (!builder.eof()) {
                val currentOffset : Int = builder.getCurrentOffset()
                if (!offsetsList.contains(currentOffset)) {
                    break
                }
                builder.advanceLexer()
            }

            mark.done(nodeType)
        } else {
            mark.drop()
        }
    }

}
