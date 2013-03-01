package org.jetbrains.agda.external

import java.util.ArrayList
import org.jetbrains.agda.lisp.LispParser
import org.jetbrains.agda.lisp.SExpression
import java.util.regex.Pattern
import java.io.File
import org.jetbrains.agda.util.FileUtil
import java.io.IOException

/**
 * Created with IntelliJ IDEA.
 * User: Atsky
 * Date: 07.01.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
class AgdaOutputProcessor {
    val messages = ArrayList<AgdaExternalAnnotation>();

    public fun processCmd(command : String) : Boolean {
        val cmdExpression : SExpression = LispParser(command).parseExpression()
        val firstString = cmdExpression.getValue(0)
        if (firstString != null && firstString.equals("agda2-info-action")) {
            parseInfoAction(cmdExpression)
        }

        if (command.contains("agda2-highlight-add-annotations"))
        {
            messages.addAll(AgdaSyntaxAnnotation.parse(cmdExpression))
        }

        if (command.contains("agda2-highlight-load-and-delete-action"))
        {
            var expr : SExpression? = LispParser(command).parseExpression()
            try
            {
                var file : File? = File(expr?.getValue(1)!!)
                var data : String? = FileUtil.readFile(file)
                file?.delete()
                var expression : SExpression? = LispParser(data).parseExpression()
                messages?.addAll(AgdaSyntaxAnnotation.parse(expression))
            }
            catch (e : IOException) {
                e?.printStackTrace()
            }

        }

        if (!command?.contains("*Type-checking*") && !command?.contains("agda2-highlight-add-annotations"))
        {
            System.out?.println("[" + command + "]")
        }

        return true
    }

    public fun parseInfoAction(expression : SExpression) : Unit {
        var errorMessage : String? = null
        if ("*Error*".equals(expression.get(1)?.getValue()))
        {
            errorMessage = expression?.get(2)?.getValue()
            messages?.add(AgdaCompilerMessage(errorMessage))
        }

        if ("*All Goals*".equals(expression?.getValue(1)))
        {
            messages.addAll(getGoals(expression?.getValue(2)!!))
        }

    }

    private fun getGoals(data : String) : List<AgdaExternalAnnotation> {
        val annotations : MutableList<AgdaExternalAnnotation> = ArrayList<AgdaExternalAnnotation>()
        var index : Int = 0
        for (str : String? in Pattern.compile("\\?[\\d]+ :").split(data))
        {
            if ((str?.length())!! > 0)
            {
                annotations.add(GoalAnnotation(index, str))
                index++
            }

        }
        return annotations
    }



}