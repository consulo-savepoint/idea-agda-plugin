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
import org.jetbrains.agda.psi.ParenthesisExpression
import org.jetbrains.agda.psi.Binding
import java.util.Collections
import org.jetbrains.agda.psi.Telescope
import org.jetbrains.agda.psi.ImplicitTelescope
import org.jetbrains.agda.psi.TypeSignature
import org.jetbrains.agda.core.expression.CAnything
import org.jetbrains.agda.scope.findDeclaration
import org.jetbrains.agda.psi.FqName
import org.jetbrains.agda.psi.impl.FqNameImpl
import org.jetbrains.agda.mixfix.TermElement
import org.jetbrains.agda.mixfix.OperationElement
import org.jetbrains.agda.psi.ArrowExpression
import org.jetbrains.agda.psi.GoalExpression
import org.jetbrains.agda.mixfix.ApplicationTreeElement
import org.jetbrains.agda.psi.findTypeDeclaration

/**
 * @author Evgeny.Kurbatsky
 */

fun checkTypesFor(val element : PsiElement) : TypeChecker<PsiElement>? {
    val typeChecker = TypeChecker<PsiElement>({ declaration ->
        convertDeclaration(declaration)
    });

    if (element is DataDeclaration) {
        val psiData : DataDeclaration = element;
        val cDataDeclaration : CDataDeclaration = convertDataDeclaration(typeChecker, psiData)
        throw RuntimeException()
    }

    if (element is FunctionTypeDeclaration) {
        convertFunctionTypeDeclaration(null, element)

        throw RuntimeException()
    }

    if (element is FunctionDeclaration) {
        val functionDeclaration : FunctionDeclaration = element;
        typeChecker.checkTypes(convertFunctionBody(typeChecker, functionDeclaration));
        return typeChecker;
    }

    return checkTypesFor(element.getParent()!!);
}


fun convertFunctionBody(typeChecker: TypeChecker<PsiElement>?, functionDeclaration : FunctionDeclaration) : CFunctionBody {
    val left : Expression = functionDeclaration.getLhs().getExpression()
    val right : Expression = functionDeclaration.getWhereExpression()!!.getExpression();
    val leftPart = convertExpression(typeChecker, left)
    val body = convertExpression(typeChecker, right)
    return CFunctionBody(leftPart, body)
}

fun convertDeclaration(element : PsiElement) : CDeclaration? {
    if (element is FunctionTypeDeclaration) {
        return convertFunctionTypeDeclaration(null, element)
    }
    val parent = element.getParent()
    if (parent is TypeSignature) {
        val ctype = convertExpression(null, parent.getExpression())
        return CTypeSignature(element.getText()!!, ctype)

    }
    if (element is FqName) {
        return null;
    }
    throw RuntimeException();
}

fun convertFunctionTypeDeclaration(typeChecker : TypeChecker<PsiElement>?, element : FunctionTypeDeclaration) : CFunctionDeclaration {
    val functionDeclaration : FunctionTypeDeclaration = element;
    val name : String = functionDeclaration.getNameDeclaration().getText()!!
    val declaration = CFunctionDeclaration(name, convertExpression(typeChecker, functionDeclaration.getExpression()!!))
    for (child in element.getParent()!!.getChildren()) {
        if (child is FunctionDeclaration) {
            val functionTypeDeclaration = findTypeDeclaration(child)
            if (functionTypeDeclaration == functionDeclaration) {
                declaration.addBody(convertFunctionBody(typeChecker, child))
            }
        }
    }
    return declaration
}

fun treeToExpression(programm : TypeChecker<PsiElement>?, treeElement : TreeElement) : CExpression {
    when (treeElement) {
        is TermElement-> return convertExpression(programm, treeElement.element)
        is OperationElement -> {
            val declaration = treeElement.declaration
            val ref : CRefExpression = CRefExpression(declaration, getDeclarationName(declaration)!!)
            val children : MutableList<CExpression> = ArrayList<CExpression>()
            for (child in treeElement.children) {
                if (child is TermElement && !child.isOperation) {
                    children.add(treeToExpression(programm, child))
                }

            }
            var current : CExpression = ref
            for (child : CExpression in children) {
                current = CApplication(current, child)
            }
            return current
        }
        is ApplicationTreeElement -> {
            return CApplication(treeToExpression(programm, treeElement.left), treeToExpression(programm, treeElement.right))
        }
        else -> throw RuntimeException()
    }
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



fun convertExpressionImpl(program : TypeChecker<PsiElement>?, expression : PsiElement) : CExpression {
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
            return CAnything()
        }
        val declaration : PsiElement? = findDeclaration(aName)
        if (declaration != null) {
            return CRefExpression(declaration, aName.getText()!!)
        }

        throw RuntimeException();
    }

    if (expression is TeleArrow) {
        val telescopeList : MutableList<Telescope> = expression.getTelescopeList()
        Collections.reverse(telescopeList);
        var result : CExpression = convertExpression(program, expression.getExpression());
        for (telescope in telescopeList) {
            if (telescope is ImplicitTelescope) {
                val implicitTelescope : ImplicitTelescope = telescope
                val cType : CExpression? = convertExpression(program, implicitTelescope.getTypeSignature().getExpression())
                val nameDeclarationList : MutableList<NameDeclaration> = implicitTelescope.getTypeSignature().getNameDeclarationList()
                Collections.reverse(nameDeclarationList)
                for (nameDeclaration : NameDeclaration? in nameDeclarationList) {
                    result = CImplicitArrowExpression(nameDeclaration?.getText()!!, cType!!, result)
                }
            }

            if (telescope is ExplicitTelescope) {
                val explicitTelescope : ExplicitTelescope = telescope
                val cType : CExpression = convertExpression(program, explicitTelescope.getTypeSignature().getExpression())
                val nameDeclarationList : MutableList<NameDeclaration> = explicitTelescope.getTypeSignature().getNameDeclarationList()
                Collections.reverse(nameDeclarationList)
                for (nameDeclaration in nameDeclarationList) {
                    result = CPiArrowExpression(nameDeclaration.getText()!!, cType, result)
                }
            }

        }
        return result
    }

    if (expression is ArrowExpression) {
        var children : Array<PsiElement> = expression.getChildren()
        var left : CExpression = convertExpression(program, children[0])
        var right : CExpression = convertExpression(program, children[1])
        return CArrowExpression(left, right)
    }

    return when (expression) {
        is ParenthesisExpression -> convertExpression(program, expression.getExpression()!!)
        is GoalExpression -> CAnything()
        is Expression -> convertExpression(program, expression.getFirstChild()!!)
        else -> throw RuntimeException();
    }
}

fun convertExpression(program : TypeChecker<PsiElement>?, expression : PsiElement) : CExpression {
    var result : CExpression = convertExpressionImpl(program, expression)
    program?.myExpressions?.put(expression, result)
    return result
}

fun convertDataDeclaration(program : TypeChecker<PsiElement>, psiData : DataDeclaration) : CDataDeclaration {
    var aType : CExpression? = convertExpression(program, psiData.getExpression()!!)
    val signatures : MutableList<CTypeSignature?> = ArrayList<CTypeSignature?>()
    for (binding : Binding? in psiData.getBindings()!!.getBindingList()) {
        var expression : CExpression? = convertExpression(program, binding?.getExpression()!!)
        for (nameDeclaration : NameDeclaration? in binding?.getNameDeclarationList()!!)
        {
            val typeSignature = CTypeSignature(nameDeclaration?.getText()!!, expression!!)
            signatures.add(typeSignature)
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
                //program.myDeclarations.put(nameDeclaration!!, constructor)
            }
        }
    }
    return cDataDeclaration
}

class ProgramBuilder() {
    public fun getTypeOf(element : PsiElement) : CExpression? {
        val typeChecker : TypeChecker<PsiElement>?  = checkTypesFor(element);
        return typeChecker?.getTypeOf(element);
    }

    public fun normalize(element : PsiElement) : CExpression? {
        val program = TypeChecker<PsiElement>({ declaration ->
            convertDeclaration(declaration)
        });
        return program.normalize(convertExpression(program, element))
    }

}