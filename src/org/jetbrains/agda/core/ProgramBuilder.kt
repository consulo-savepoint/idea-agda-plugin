package org.jetbrains.agda.core

import com.intellij.psi.PsiElement
import org.jetbrains.agda.psi.DataDeclaration
import com.intellij.psi.PsiFile
import org.jetbrains.agda.psi.FunctionTypeDeclaration
import java.util.ArrayList
import org.jetbrains.agda.psi.Application
import org.jetbrains.agda.mixfix.Grammar
import org.jetbrains.agda.psi.FunctionDeclaration
import org.jetbrains.agda.psi.Expression
import org.jetbrains.agda.mixfix.TreeElement
import org.jetbrains.agda.psi.NameDeclaration
import org.jetbrains.agda.psi.impl.ANameImpl
import org.jetbrains.agda.psi.TeleArrow
import org.jetbrains.agda.psi.ExplicitTelescope
import org.jetbrains.agda.psi.FunctionType
import org.jetbrains.agda.psi.ParenthesisExpression
import org.jetbrains.agda.psi.Binding
import java.util.Collections
import org.jetbrains.agda.psi.Telescope
import org.jetbrains.agda.psi.ImplicitTelescope
import org.jetbrains.agda.psi.TypeSignature
import org.jetbrains.agda.core.expression.CMetaVariable
import org.jetbrains.agda.scope.findDeclaration

/**
 * @author Evgeny.Kurbatsky
 */

fun buildProgram(var element : PsiElement?) : Program<PsiElement> {
    while (!((element is PsiFile?)))
    {
        element = element?.getParent()
    }
    val program = Program<PsiElement>()
    buildFromRoot(program, element)
    return program
}


fun buildFromRoot(program : Program<PsiElement>, element : PsiElement?) : Unit {

    if (element is DataDeclaration) {
        var psiData : DataDeclaration = element;
        var cDataDeclaration : CDataDeclaration = convertDataDeclaration(program, psiData)
        program.myDeclarations.put(psiData, cDataDeclaration)
    }

    if (element is FunctionTypeDeclaration) {
        var functionDeclaration : FunctionTypeDeclaration = element;
        val name : String = functionDeclaration.getNameDeclaration().getText()!!
        val declaration = CFunctionDeclaration(name, convertExpression(program, functionDeclaration.getExpression()!!))
        program.myLassDeclaration = declaration
        program.myDeclarations.put(functionDeclaration, declaration)
    }

    if (element is FunctionDeclaration) {
        var functionDeclaration : FunctionDeclaration = element;
        val left : Expression? = functionDeclaration.getLhs().getExpression()
        val right : Expression? = functionDeclaration.getExpression()
        val leftPart : CExpression? = convertExpression(program, left!!)
        val body : CExpression? = convertExpression(program, right!!)
        program.myLassDeclaration?.getBodyes()?.add(FunctionBody(leftPart!!, body!!))
    }

    if (element is PsiFile) {
        for (child : PsiElement? in element.getChildren()) {
            buildFromRoot(program, child)
        }
    }

}

fun treeToExpression(programm : Program<PsiElement>, treeElement : TreeElement?) : CExpression {
    if (treeElement?.getDeclaration() != null)
    {
        var declaration : PsiElement? = treeElement?.getDeclaration()
        var ref : CRefExpression = CRefExpression(declaration!!, getDeclarationName(declaration)!!)
        var children : MutableList<CExpression?>? = ArrayList<CExpression?>()
        for (child : TreeElement? in treeElement?.getChildren()!!) {
            if (! child!!.isTerm()) {
                children?.add(treeToExpression(programm, child))
            }

        }
        var current : CExpression = ref
        for (child : CExpression? in children!!) {
            current = CApplication(current, child!!)
        }
        return current
    } else {
        if (treeElement?.getElement() != null) {
            return convertExpression(programm, treeElement?.getElement()!!)
        }
        else {
            var children : Array<TreeElement?>? = treeElement?.getChildren()
            if ((children?.size)!! == 1) {
                return treeToExpression(programm, children!![0])
            } else if ((children?.size)!! == 2) {
                return CApplication(treeToExpression(programm, children!![0]!!), treeToExpression(programm, children!![1]!!))
            }
        }
    }
    throw RuntimeException()
}
private fun getDeclarationName(declaration : PsiElement?) : String? {
    if (declaration is FunctionTypeDeclaration) {
        return declaration.getNameDeclaration().getText()
    }

    if (declaration is DataDeclaration) {
        return declaration.getNameDeclaration()?.getText()
    }

    if (declaration is NameDeclaration) {
        return declaration.getText()
    }

    throw IllegalArgumentException()
}



fun parseExpressionImpl(program : Program<PsiElement>, expression : PsiElement?) : CExpression {
    if ((expression is Application?)) {
        var treeElement : TreeElement? = Grammar.parse((expression as Application?))
        return treeToExpression(program, treeElement)
    }

    if (expression is ANameImpl) {
        var aName : ANameImpl = expression
        if (aName.getText()?.equals("Set")!!)
        {
            return CSet()
        }
        if (aName.getText()?.equals("_")!!) {
            return CMetaVariable()
        }
        val declaration : PsiElement? = findDeclaration(aName)
        if (declaration != null) {
            return CRefExpression(declaration, aName.getText()!!)
        }

        throw RuntimeException();
    }

    if (expression is TeleArrow) {
        var telescopeList : MutableList<Telescope?> = expression.getTelescopeList()
        Collections.reverse(telescopeList);
        var result : CExpression = convertExpression(program, expression.getExpression());
        for (telescope : Telescope? in telescopeList)
        {
            if (telescope is ImplicitTelescope) {
                var implicitTelescope : ImplicitTelescope? = (telescope as ImplicitTelescope?)
                var cType : CExpression? = convertExpression(program, implicitTelescope?.getTypeSignature()?.getExpression()!!)
                var nameDeclarationList : MutableList<NameDeclaration?> = implicitTelescope?.getTypeSignature()?.getNameDeclarationList()!!
                Collections.reverse(nameDeclarationList)
                for (nameDeclaration : NameDeclaration? in nameDeclarationList)
                {
                    result = CImplicitArrowExpression(nameDeclaration?.getText()!!, cType!!, result)
                }
            }

            if ((telescope is ExplicitTelescope?))
            {
                var explicitTelescope : ExplicitTelescope? = (telescope as ExplicitTelescope?)
                var cType : CExpression = convertExpression(program, explicitTelescope?.getTypeSignature()?.getExpression()!!)
                var nameDeclarationList : MutableList<NameDeclaration?> = explicitTelescope?.getTypeSignature()?.getNameDeclarationList()!!
                Collections.reverse(nameDeclarationList)
                for (nameDeclaration : NameDeclaration? in nameDeclarationList)
                {
                    result = CPiArrowExpression(nameDeclaration?.getText()!!, cType, result)
                }
            }

        }
        return result
    }

    if (expression is FunctionType) {
        var children : Array<PsiElement?> = expression.getChildren()
        var left : CExpression = convertExpression(program, children[0]!!)
        var right : CExpression = convertExpression(program, children[1]!!)
        return CArrowExpression(left, right)
    }

    if (expression is ParenthesisExpression) {
        return convertExpression(program, expression.getExpression()!!)
    }

    val firstChild : PsiElement? = expression?.getFirstChild()
    if (firstChild != null) {
        return convertExpression(program, firstChild)
    }
    else throw RuntimeException()
}

fun convertExpression(program : Program<PsiElement>, expression : PsiElement) : CExpression {
    var result : CExpression = parseExpressionImpl(program, expression)
    program.myExpressions.put(expression, result)
    return result
}

fun convertDataDeclaration(program : Program<PsiElement>, psiData : DataDeclaration?) : CDataDeclaration {
    var aType : CExpression? = convertExpression(program, psiData?.getExpression()!!)
    val signatures : MutableList<CTypeSignature?> = ArrayList<CTypeSignature?>()
    for (binding : Binding? in psiData?.getBindingList()!!)
    {
        var expression : CExpression? = convertExpression(program, binding?.getExpression()!!)
        for (nameDeclaration : NameDeclaration? in binding?.getNameDeclarationList()!!)
        {
            val typeSignature = CTypeSignature(nameDeclaration?.getText()!!, expression!!)
            signatures.add(typeSignature)
            program.myDeclarations.put(nameDeclaration!!, typeSignature)
        }
    }
    Collections.reverse(signatures)
    for (signature : CTypeSignature? in signatures)
    {
        aType = CPiArrowExpression(signature?.getName()!!, signature!!.aType, aType!!)
    }
    var cDataDeclaration : CDataDeclaration = CDataDeclaration(psiData?.getNameDeclaration()?.getText()!!, aType!!)
    for (typeSignature : TypeSignature? in psiData?.getConstructors()?.getTypeSignatureList()!!)
    {
        var ctype : CExpression? = convertExpression(program, typeSignature?.getExpression()!!)
        for (signature : CTypeSignature? in signatures)
        {
            ctype = CImplicitArrowExpression(signature?.getName()!!, signature!!.aType, ctype!!)
        }
        for (nameDeclaration : NameDeclaration? in typeSignature?.getNameDeclarationList()!!)
        {
            val constructor : CTypeSignature = CTypeSignature(nameDeclaration?.getText()!!, ctype!!)
            cDataDeclaration.getConstructors().add(constructor)
            program.myDeclarations.put(nameDeclaration!!, constructor)
        }
    }
    return cDataDeclaration
}

class ProgramBuilder() {
    public fun build(element : PsiElement): Program<PsiElement>? {
        return buildProgram(element);
    }
}