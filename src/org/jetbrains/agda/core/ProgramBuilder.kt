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
import org.jetbrains.agda.util.FList
import org.jetbrains.agda.util.Nil
import org.jetbrains.agda.util.Cons
import com.intellij.util.containers.hash.HashMap

/**
 * @author Evgeny.Kurbatsky
 */

class ProgramBuilder() {
    val myExpressions : MutableMap<PsiElement, CExpression> = HashMap<PsiElement, CExpression>()

    fun convertExpression(expression : PsiElement) : CExpression {
        var result : CExpression = convertExpressionImpl(expression)
        myExpressions.put(expression, result)
        return result
    }

    public fun getTypeOf(element : PsiElement) : CExpression? {
        val typeChecker : TypeChecker?  = checkTypesFor(element);
        throw RuntimeException()
    }

    public fun normalize(element : PsiElement) : CExpression? {
        val program = TypeChecker({ declaration ->
            convertDeclaration(declaration as PsiElement)
        });
        return program.normalize(convertExpression(element))
    }

    fun convertExpressionImpl(expression : PsiElement) : CExpression {
        if (expression is Application) {
            val treeElement : TreeElement? = Grammar.parse(expression)
            return treeToExpression(treeElement!!)
        }

        if (expression is FqNameImpl) {
            var aName : FqNameImpl = expression
            if (aName.getText()?.equals("Set")!!)
            {
                return CSet(0)
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
            var result : CExpression = convertExpression(expression.getExpression());
            for (telescope in telescopeList) {
                if (telescope is ImplicitTelescope) {
                    var implicitTelescope : ImplicitTelescope = (telescope as ImplicitTelescope)
                    var cType : CExpression? = convertExpression(implicitTelescope.getTypeSignature().getExpression())
                    var nameDeclarationList : MutableList<NameDeclaration> = implicitTelescope.getTypeSignature().getNameDeclarationList()
                    Collections.reverse(nameDeclarationList)
                    for (nameDeclaration : NameDeclaration? in nameDeclarationList) {
                        result = CImplicitArrowExpression(nameDeclaration?.getText()!!, cType!!, result)
                    }
                }

                if (telescope is ExplicitTelescope) {
                    val explicitTelescope : ExplicitTelescope = telescope as ExplicitTelescope
                    var cType : CExpression = convertExpression(explicitTelescope.getTypeSignature().getExpression())
                    var nameDeclarationList : MutableList<NameDeclaration> = explicitTelescope.getTypeSignature().getNameDeclarationList()
                    Collections.reverse(nameDeclarationList)
                    for (nameDeclaration : NameDeclaration? in nameDeclarationList)
                    {
                        result = CPiArrowExpression(nameDeclaration?.getText()!!, cType, result)
                    }
                }

            }
            return result
        }

        if (expression is ArrowExpression) {
            var children : Array<PsiElement> = expression.getChildren()
            var left : CExpression = convertExpression(children[0])
            var right : CExpression = convertExpression(children[1])
            return CArrowExpression(left, right)
        }

        return when (expression) {
            is ParenthesisExpression -> convertExpression(expression.getExpression()!!)
            is GoalExpression -> CAnything()
            is Expression -> convertExpression(expression.getFirstChild()!!)
            else -> throw RuntimeException();
        }
    }

    fun convertFunctionBody(functionDeclaration : FunctionDeclaration) : CFunctionBody {
        val left : Expression = functionDeclaration.getLhs().getExpression()
        val right : Expression = functionDeclaration.getWhereExpression()!!.getExpression();
        val leftPart = convertExpression(left)
        val body = convertExpression(right)
        return CFunctionBody(leftPart, body)
    }

    fun convertDeclaration(element : PsiElement) : CDeclaration? {
        if (element is FunctionTypeDeclaration) {
            return convertFunctionTypeDeclaration(element)
        }
        val parent = element.getParent()
        if (parent is TypeSignature) {
            val ctype = convertExpression(parent.getExpression())
            return CTypeSignature(element.getText()!!, ctype)

        }
        if (element is FqName) {
            return null;
        }
        throw RuntimeException();
    }

    fun checkTypesFor(element : PsiElement) : TypeChecker {
        val typeChecker = TypeChecker({ declaration ->
            convertDeclaration(declaration as PsiElement)
        });

        if (element is DataDeclaration) {
            val psiData : DataDeclaration = element;
            val cDataDeclaration : CDataDeclaration = convertDataDeclaration(psiData)
            throw RuntimeException()
        }

        if (element is FunctionTypeDeclaration) {
            convertFunctionTypeDeclaration(element)

            throw RuntimeException()
        }

        if (element is FunctionDeclaration) {
            val functionDeclaration : FunctionDeclaration = element;
            typeChecker.checkTypes(convertFunctionBody(functionDeclaration));
            return typeChecker;
        }

        return checkTypesFor(element.getParent()!!);
    }

    fun convertFunctionTypeDeclaration(element : FunctionTypeDeclaration) : CFunctionDeclaration {
        val functionDeclaration : FunctionTypeDeclaration = element;
        val name : String = functionDeclaration.getNameDeclaration().getText()!!
        val bodies = ArrayList<CFunctionBody>()
        for (child in element.getParent()!!.getChildren()) {
            if (child is FunctionDeclaration) {
                val functionTypeDeclaration = findTypeDeclaration(child)
                if (functionTypeDeclaration == functionDeclaration) {
                    bodies.add(convertFunctionBody(child))
                }
            }
        }
        val declaration = CFunctionDeclaration(name, convertExpression(functionDeclaration.getExpression()!!), bodies)
        return declaration
    }

    fun treeToExpression(treeElement : TreeElement) : CExpression {
        when (treeElement) {
            is TermElement-> return convertExpression(treeElement.element)
            is OperationElement -> {
                val declaration = treeElement.declaration
                val ref : CRefExpression = CRefExpression(declaration, getDeclarationName(declaration))
                val children : MutableList<CExpression> = ArrayList<CExpression>()
                for (child in treeElement.children) {
                    if (child is TermElement && !child.isOperation) {
                        children.add(treeToExpression(child))
                    }

                }
                var current : CExpression = ref
                for (child : CExpression in children) {
                    current = CApplication(current, child)
                }
                return current
            }
            is ApplicationTreeElement -> {
                return CApplication(treeToExpression(treeElement.left), treeToExpression(treeElement.right))
            }
            else -> throw RuntimeException()
        }
    }

    private fun getDeclarationName(declaration : PsiElement) : String {
        if (declaration is FunctionTypeDeclaration) {
            return declaration.getNameDeclaration().getText()!!
        }

        if (declaration is DataDeclaration) {
            return declaration.getNameDeclaration()?.getText()!!
        }

        if (declaration is NameDeclaration) {
            return declaration.getText()!!
        }

        throw IllegalArgumentException()
    }

    fun convertDataDeclaration(psiData : DataDeclaration) : CDataDeclaration {
        var aType : CExpression? = convertExpression(psiData.getExpression()!!)
        val signatures : MutableList<CTypeSignature?> = ArrayList<CTypeSignature?>()
        for (binding : Binding? in psiData.getBindings()!!.getBindingList()) {
            var expression : CExpression? = convertExpression(binding?.getExpression()!!)
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
                var ctype : CExpression? = convertExpression(typeSignature.getExpression())
                for (signature : CTypeSignature? in signatures) {
                    ctype = CImplicitArrowExpression(signature?.getName()!!, signature!!.aType, ctype!!)
                }
                for (nameDeclaration : NameDeclaration? in typeSignature.getNameDeclarationList()) {
                    val constructor : CTypeSignature = CTypeSignature(nameDeclaration?.getText()!!, ctype!!)
                    cDataDeclaration.getConstructors().add(constructor)
                    //program.myDeclarations.put(nameDeclaration!!, constructor)
                }
            }
        }
        return cDataDeclaration
    }

}