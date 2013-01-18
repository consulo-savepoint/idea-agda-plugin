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
import org.jetbrains.agda.psi.FqName
import org.jetbrains.agda.psi.impl.FqNameImpl
import org.jetbrains.agda.mixfix.TermElement
import org.jetbrains.agda.mixfix.ParentElement

/**
 * @author Evgeny.Kurbatsky
 */

fun buildProgram(val element : PsiElement) : Program<PsiElement> =
    if (element is PsiFile) {
        val program = Program<PsiElement>()
        buildFromRoot(program, element)
        program
    } else {
        buildProgram(element.getParent()!!);
    }



fun buildFromRoot(program : Program<PsiElement>, element : PsiElement) : Unit {
    if (element is DataDeclaration) {
        val psiData : DataDeclaration = element;
        val cDataDeclaration : CDataDeclaration = convertDataDeclaration(program, psiData)
        program.myDeclarations.put(psiData, cDataDeclaration)
    }

    if (element is FunctionTypeDeclaration) {
        val functionDeclaration : FunctionTypeDeclaration = element;
        val name : String = functionDeclaration.getNameDeclaration().getText()!!
        val declaration = CFunctionDeclaration(name, convertExpression(program, functionDeclaration.getExpression()!!))
        program.myDeclarations.put(functionDeclaration, declaration)
    }

    if (element is FunctionDeclaration) {
        val functionDeclaration : FunctionDeclaration = element;
        val left : Expression = functionDeclaration.getLhs().getExpression()
        val right : Expression = functionDeclaration.getWhereEpression()!!.getExpression();
        val leftPart = convertExpression(program, left)
        val body = convertExpression(program, right)
        val functionBody = CFunctionBody(leftPart, body)
        (program.myDeclarations[functionBody.getDeclaration()] as CFunctionDeclaration).addBody(functionBody)
    }

    if (element is PsiFile) {
        for (child in element.getChildren()) {
            buildFromRoot(program, child!!)
        }
    }

}

fun treeToExpression(programm : Program<PsiElement>, treeElement : TreeElement) : CExpression {
    when (treeElement) {
        is TermElement-> return convertExpression(programm, treeElement.element)
        is ParentElement -> {
            if (treeElement.getDeclaration() != null) {
                val declaration : PsiElement? = treeElement.getDeclaration()
                val ref : CRefExpression = CRefExpression(declaration!!, getDeclarationName(declaration)!!)
                val children : MutableList<CExpression> = ArrayList<CExpression>()
                for (child : TreeElement? in treeElement.getChildren()) {
                    if (! child!!.isTerm()) {
                        children.add(treeToExpression(programm, child))
                    }

                }
                var current : CExpression = ref
                for (child : CExpression in children) {
                    current = CApplication(current, child)
                }
                return current
            } else {
                val children = treeElement.getChildren()
                if ((children.size) == 1) {
                    return treeToExpression(programm, children[0])
                } else if ((children.size) == 2) {
                    return CApplication(treeToExpression(programm, children[0]), treeToExpression(programm, children[1]))
                }
            }
        }
        else -> throw RuntimeException()
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



fun convertExpressionImpl(program : Program<PsiElement>, expression : PsiElement?) : CExpression {
    if (expression is Application) {
        val treeElement : TreeElement? = Grammar.parse(expression)
        return treeToExpression(program, treeElement!!)
    }

    if (expression is FqNameImpl) {
        var aName : FqNameImpl = expression
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

    if (expression is Expression) {
        return convertExpression(program, expression.getFirstChild()!!)
    }
    else throw RuntimeException()
}

fun convertExpression(program : Program<PsiElement>, expression : PsiElement) : CExpression {
    var result : CExpression = convertExpressionImpl(program, expression)
    program.myExpressions.put(expression, result)
    return result
}

fun convertDataDeclaration(program : Program<PsiElement>, psiData : DataDeclaration) : CDataDeclaration {
    var aType : CExpression? = convertExpression(program, psiData.getExpression()!!)
    val signatures : MutableList<CTypeSignature?> = ArrayList<CTypeSignature?>()
    for (binding : Binding? in psiData.getBindings()!!.getBindingList()) {
        var expression : CExpression? = convertExpression(program, binding?.getExpression()!!)
        for (nameDeclaration : NameDeclaration? in binding?.getNameDeclarationList()!!)
        {
            val typeSignature = CTypeSignature(nameDeclaration?.getText()!!, expression!!)
            signatures.add(typeSignature)
            program.myDeclarations.put(nameDeclaration!!, typeSignature)
        }
    }
    Collections.reverse(signatures)
    for (signature : CTypeSignature? in signatures) {
        aType = CPiArrowExpression(signature?.getName()!!, signature!!.aType, aType!!)
    }
    val cDataDeclaration : CDataDeclaration = CDataDeclaration(psiData.getNameDeclaration()?.getText()!!, aType!!)
    val constructors = psiData.getConstructors()
    if (constructors != null) {
        for (typeSignature in constructors.getTypeSignatureList()) {
            var ctype : CExpression? = convertExpression(program, typeSignature?.getExpression()!!)
            for (signature : CTypeSignature? in signatures) {
                ctype = CImplicitArrowExpression(signature?.getName()!!, signature!!.aType, ctype!!)
            }
            for (nameDeclaration : NameDeclaration? in typeSignature?.getNameDeclarationList()!!) {
                val constructor : CTypeSignature = CTypeSignature(nameDeclaration?.getText()!!, ctype!!)
                cDataDeclaration.getConstructors().add(constructor)
                program.myDeclarations.put(nameDeclaration!!, constructor)
            }
        }
    }
    return cDataDeclaration
}

class ProgramBuilder() {
    public fun build(element : PsiElement): Program<PsiElement>? {
        return buildProgram(element);
    }
}