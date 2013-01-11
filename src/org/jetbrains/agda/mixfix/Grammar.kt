package org.jetbrains.agda.mixfix

import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.HashSet
import org.jetbrains.agda.scope.AgdaExpressionScope
import org.jetbrains.agda.psi.Application
import java.util.Collections
import java.util.Arrays


public open class Grammar(val myDeclarations : MutableMap<String, PsiElement>) {
    private var myOperatorParts : ArrayList<String>? = ArrayList<String>()

    private open fun parse(listOfTerminals : List<PsiElement>) : TreeElement? {
        val list = ArrayList<TreeElement>()
        for (element : PsiElement? in listOfTerminals)
        {
            list.add(TreeElement(element, null, Collections.emptyList()))
        }
        val term = parseIterative(parseApplications(list))
        return term
    }

    private open fun parseApplications(list : MutableList<TreeElement>) : List<TreeElement> {
        val result = ArrayList<TreeElement>()
        var current : TreeElement? = null
        for (term : TreeElement? in list) {
            if (myOperatorParts?.contains(term?.getText())!!)
            {
                if (current != null)
                {
                    result.add(current!!)
                    current = null
                }

                result.add(term!!)
            }
            else
            {
                if (current == null)
                {
                    current = TreeElement(null, null, Collections.singletonList(term))
                }
                else
                {
                    current = TreeElement(null, null, Arrays.asList(current!!, TreeElement(null, null, Collections.singletonList(term))))
                }
            }
        }
        if (current != null)
        {
            result?.add(current!!)
        }

        return result
    }
    private open fun parseIterative(list : List<TreeElement>) : TreeElement? {
        if ((list?.size())!! == 1)
        {
            return list?.get(0)
        }

        var good : Boolean = true
        for (treeElement : TreeElement? in list!!)
        {
            if (myOperatorParts?.contains(treeElement?.getText())!!)
            {
                good = false
                break
            }

        }
        if (good)
        {
            var current : TreeElement? = null
            for (treeElement : TreeElement? in list)
            {
                if (current == null)
                {
                    current = treeElement
                }
                else
                {
                    current = TreeElement(null, null, Arrays.asList(current!!, treeElement!!))
                }
            }
            return current
        }

        for (i in 0..list.size() - 1) {
            var term : TreeElement? = applyRule(list!!, i)
            if (term != null)
            {
                return term
            }

        }
        return null
    }
    private open fun applyRule(list : List<TreeElement>?, i : Int) : TreeElement? {
        for (rule : String? in myDeclarations?.keySet()!!)
        {
            if (rule?.equals("_")!!)
            {
                continue
            }

            var ruleCode : List<String> = decode(rule!!)
            var length : Int = (ruleCode!!.size) - 1
            if (i >= length)
            {
                var matches : Boolean = true
                for (j in 0..ruleCode!!.size - 1) {
                    if (ruleCode!![j].equals("_"))
                    {
                        if (myOperatorParts?.contains(list?.get(i + j - length)?.getText())!!)
                        {
                            matches = false
                            break
                        }

                    }
                    else
                    {
                        if (!ruleCode!![j].equals(list?.get(i + j - length)?.getText()))
                        {
                            matches = false
                            break
                        }

                    }
                }
                if (matches)
                {
                    var termList : List<TreeElement> = list!!.subList(i - length, i + 1)
                    var term : TreeElement? = TreeElement(null, myDeclarations?.get(rule)!!, termList)
                    return parseIterative(replace(list, i, length, term!!))
                }

            }

        }
        return null
    }
    private open fun replace(list : List<TreeElement>, i : Int, size : Int, v : TreeElement) : List<TreeElement> {
        var terms = ArrayList<TreeElement>(list)
        for (j in 0..size) {
            terms.remove(i - size)
        }
        terms.add(i - size, v)
        return terms
    }

    {
        for (declaration : String? in HashSet<String>(myDeclarations!!.keySet()))
        {
            if (!declaration!!.contains("_"))
            {
                myDeclarations?.remove(declaration)
            }

        }
        var operationParts : Set<String> = getOperationParts(myDeclarations!!)!!
        myOperatorParts?.addAll(operationParts)
    }

    class object {
        public open fun getOperationParts(declarations : Map<String, PsiElement>) : Set<String>? {
            var operationParts : MutableSet<String>? = HashSet<String>()
            for (declaration : String? in declarations.keySet())
            {
                if (!declaration!!.contains("_"))
                {
                    continue
                }

                for (str : String? in decode(declaration))
                {
                    if (!str!!.equals("_")) {
                        operationParts?.add(str!!)
                    }

                }
            }
            return operationParts
        }
        private open fun getListOfTerminals(var application : Application?) : List<PsiElement>? {
            while ((application?.getParent() is Application?))
            {
                application = (application?.getParent() as Application?)
            }
            val result = ArrayList<PsiElement>()
            var element : PsiElement? = application
            while ((element is Application?)) {
                var expressionList : Array<PsiElement?>? = element!!.getChildren()
                result?.add(expressionList!![0]!!)
                if ((expressionList!!.size)!! > 1)
                {
                    element = expressionList!![1]
                }
                else
                {
                    break
                }
            }
            result.add(element!!)
            return result
        }

        public open fun parse(application : Application) : TreeElement? {
            val listOfTerminals : List<PsiElement>? = getListOfTerminals(application)
            val scope = AgdaExpressionScope(application)
            val declarations : MutableMap<String, PsiElement> = scope.getVisibleDeclarations()
            return Grammar(declarations).parse(listOfTerminals!!)
        }

        private open fun decode(rule : String) : List<String> {
            if (rule?.contains("_")!!) {
                var result = ArrayList<String>()
                var start : Int = 0
                for (i in 0..rule!!.length() - 1) {
                    if ((rule.charAt(i))!! == '_')
                    {
                        if (start < i)
                        {
                            result!!.add(rule!!.substring(start, i))
                        }

                        result.add("_")
                        start = i + 1
                    }
                    else
                    {
                        if ((i + 1) == (rule?.length())!!)
                        {
                            result!!.add(rule!!.substring(start, i + 1))
                        }

                    }
                }
                return result!!
            }
            else
            {
                return Collections.singletonList(rule)
            }
        }
    }
}
