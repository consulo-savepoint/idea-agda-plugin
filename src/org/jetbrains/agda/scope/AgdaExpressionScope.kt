package org.jetbrains.agda.scope

/**
 * @author Evgeny.Kurbatsky
 */
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.agda.mixfix.Grammar
import java.util.HashMap
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration
import org.jetbrains.agda.psi.*
import java.util.Collections
import java.util.ArrayList


public class AgdaExpressionScope(val element : PsiElement) : Scope() {
    fun NameDeclaration.getPair() =  Pair<String, PsiElement>(this.getText()!!, this)

    override fun traverse(function : (String, PsiElement) -> Boolean) =
        ArdaExpressionScopeTraverse(function).traverse()

    private fun getDeclarationsOld() : MutableMap<String, PsiElement> {
        val declarations : MutableMap<String, PsiElement> = HashMap<String, PsiElement>()

        declarations.putAll(AgdaExpressionScope(element.getParent()!!).getVisibleDeclarations());

        val current : PsiElement = element
        if (element is TeleArrow) {
            for (telescope in element.getTelescopeList()) {
                val typeSignature : TypeSignature
                if (telescope is ImplicitTelescope) {
                    typeSignature = (telescope as ImplicitTelescope).getTypeSignature()
                } else {
                    typeSignature = (telescope as ExplicitTelescope).getTypeSignature()
                }
                for (element in typeSignature.getNameDeclarationList()) {
                    declarations.put(element!!.getText()!!, element)
                }
            }
        }

        if ((current is LambdaExpression?)) {
                var expression : LambdaExpression? = (current as LambdaExpression?)
                for (nameDeclaration : NameDeclaration? in expression?.getNameDeclarationList()!!)
                {
                    declarations.put(nameDeclaration?.getText()!!, nameDeclaration!!)
                }
        }

        if ((current is LetExpression?))
            {
                var letExpression : LetExpression? = (current as LetExpression?)
                var nameDeclaration : NameDeclaration? = letExpression?.getNameDeclaration()
                declarations.put(nameDeclaration?.getText()!!, nameDeclaration!!)
            }


        val parent = element.getParent()

        if (parent is RecordDeclaration) {
            val recordDeclaration = parent as RecordDeclaration
            val typedUntypedBindingList = recordDeclaration.getTypedUntypedBindingList()
            for (binding in typedUntypedBindingList) {
                val typeSignature = binding!!.getTypeSignature()
                if (typeSignature != null) {
                    for (declaration in typeSignature.getNameDeclarationList()) {
                        declarations.put(declaration?.getText()!!, declaration!!)
                    }
                }
                for (declaration in binding.getNameDeclarationList()) {
                    declarations.put(declaration?.getText()!!, declaration!!)
                }
            }
        }

        if (parent is FunctionDeclaration) {
            val declaration = parent as FunctionDeclaration
            addFunctionParameters(declaration.getLhs().getExpression(), declarations, Grammar.getOperationParts(declarations))
        }

        if (parent is Bindings) {
            val bindingsList = parent.getBindingList().map { it!! }.takeWhile { it != element }
            addBindings(bindingsList, declarations)
        }

        getVisibleDeclarationsFunctional().forEach { declarations.putAll(it) }

        return declarations;
    }

    public fun getVisibleDeclarationsFunctional() : List<Pair<String, PsiElement>> {
        val parent = element.getParent()

        if (parent is TypeSignatures) {
            val declarations = ArrayList<Pair<String, PsiElement>>()

            for (typeSignature in parent.getTypeSignatureList().takeWhile { it != element }) {
                declarations.addAll(typeSignature!!.getNameDeclarationList().map { it!!.getPair() })
            }
            return declarations;
        }
        return Collections.emptyList();
    }

    fun addBindings(bindingsList : List<Binding>, declarations : MutableMap<String, PsiElement>) {
        for (binding in bindingsList) {
            for (nameDeclaration in binding.getNameDeclarationList()) {
                declarations.put(nameDeclaration!!.getText()!!, nameDeclaration)
            }
        }
    }

    private fun addFunctionParameters(expression : PsiElement, declarations : MutableMap<String, PsiElement>, operationParts : Set<String?>?) : Unit {
        if (expression is FqName) {
            val text : String? = expression.getText();
            if (!declarations.containsKey(text) && !operationParts!!.contains(text)) {
                declarations.put(text!!, expression)
            }
        } else {
            for (child : PsiElement? in expression.getChildren()) {
                addFunctionParameters(child!!, declarations, operationParts)
            }
        }
    }

    class ArdaExpressionScopeTraverse(val function : (String, PsiElement) -> Boolean) {

        fun traverse() : Boolean {
            val parent = element.getParent()

            val result = when (parent) {
                is PsiFile -> {
                    return AgdaModuleScope(parent, false).traverse(function)
                }
                is WhereExpression -> {
                    val wherePart = parent.getWherePart()
                    if (wherePart != null) {AgdaModuleScope(wherePart, false).traverse(function)} else false;
                };
                is ForallExpression -> {
                    val expression : ForallExpression = (parent as ForallExpression)
                    traverseList(expression.getTypedUntypedBindingList()) {
                        traverseTypedUntypedBinding(it!!);
                    }
                }
                is DataDeclaration -> {
                    traverseList(parent.getBindings()!!.getBindingList().takeWhile { it != element }) {
                        traverseNameDeclarations(it!!.getNameDeclarationList())
                    }
                }
                is ModuleDeclaration -> {
                    var t1 = traverseList((parent.getBindings()!!.getBindingList().takeWhile { it != element })) {
                        traverseNameDeclarations(it!!.getNameDeclarationList())
                    }
                    t1 || AgdaModuleScope(parent, false).traverse(function);
                }
                else -> false;
            }

            return result || traverseMap(getDeclarationsOld());
        }

        fun traverseMap(map : Map<String, PsiElement>) : Boolean {
            for ((name, value) in map) {
                if (function(name, value)) {
                    return true;
                }
            }
            return false;
        }

        fun traverseTypedUntypedBinding(typedUntypedBinding : TypedUntypedBinding) : Boolean {
            val typeSignature = typedUntypedBinding.getTypeSignature()
            val nameDeclarations = if (typeSignature != null) {
                typeSignature.getNameDeclarationList();
            } else {
                typedUntypedBinding.getNameDeclarationList()
            }
            return traverseNameDeclarations(nameDeclarations);
        }

        fun traverseNameDeclarations(nameDeclarations : List<NameDeclaration?>) : Boolean {
            traverseList (nameDeclarations) {
                function(it!!.getText()!!, it)
            }
            return false;
        }



    }
}

