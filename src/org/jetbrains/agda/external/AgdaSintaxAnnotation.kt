package org.jetbrains.agda.external

import org.jetbrains.agda.util.lisp.SExpression
import java.util.ArrayList


public open class AgdaSyntaxAnnotation(start : Int, end : Int, `type` : String?, referenceFile : String?, referenceIndex : Int) : AgdaExternalAnnotation() {
    private var myStart : Int = 0
    private var myEnd : Int = 0
    private var myType : String? = null
    private var myReference : String? = null
    private var myReferenceIndex : Int = 0
    public open fun getType() : String? {
        return myType
    }
    public open fun getStart() : Int {
        return myStart
    }
    public open fun getEnd() : Int {
        return myEnd
    }
    public override fun toString() : String? {
        return "(" + myStart + ":" + myEnd + " - " + myType + ")"
    }
    public open fun getReference() : String? {
        return myReference
    }
    public open fun getReferenceIndex() : Int {
        return myReferenceIndex
    }
    {
        myStart = start
        myEnd = end
        myType = `type`
        myReference = referenceFile
        myReferenceIndex = referenceIndex
    }
    class object {
        public open fun parse(expr : SExpression?) : List<AgdaSyntaxAnnotation> {
            val result : MutableList<AgdaSyntaxAnnotation> = ArrayList<AgdaSyntaxAnnotation>()
            for (expression : SExpression? in expr?.getChildren()!!)
            {
                if (expression?.isAtom()!!)
                {
                    continue
                }

                var start : Int = (Integer.parseInt(expression?.get(0)?.getValue()!!)) - 1
                var end : Int = (Integer.parseInt(expression?.get(1)?.getValue()!!)) - 1
                var expression1 : SExpression? = expression?.get(2)?.get(0)
                if (expression1 == null)
                {
                    continue
                }

                var `type` : String? = expression1?.getValue()
                var referenceFile : String? = null
                var referenceIndex : Int = 0
                if ((expression?.getChildren()?.size())!! == 5)
                {
                    var ref : SExpression? = expression?.get(4)
                    referenceFile = ref?.get(0)?.getValue()
                    referenceIndex = (Integer.parseInt(ref?.get(2)?.getValue()!!)) - 1
                }

                result.add(AgdaSyntaxAnnotation(start, end, `type`, referenceFile, referenceIndex))
            }
            return result
        }
    }
}
