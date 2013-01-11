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

    override fun traverse(function : (String, PsiElement) -> Boolean) : Boolean {
        val parent = element.getParent()

        val result = when (parent) {
            is WhereEpression -> {
                val wherePart = parent.getWherePart()
                if (wherePart != null) {AgdaModuleScope(wherePart).traverse(function)} else false;
            };
            else -> false;
        }
        if (result) {
            return true;
        }

        for ((name, value) in getDeclarationsOld()) {
            if (function(name, value)) {
                return true;
            }
        }
        return false;
    }

    private fun getDeclarationsOld() : MutableMap<String, PsiElement> {

        if (element is PsiFile) {
            return AgdaModuleScope(element).getVisibleDeclarations();
        }

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

        if ((current is ForallExpression?)) {
                var expression : ForallExpression? = (current as ForallExpression?)
                for (typedUntypedBinding : TypedUntypedBinding? in expression?.getTypedUntypedBindingList()!!)
                {
                    for (nameDeclaration : NameDeclaration? in typedUntypedBinding?.getNameDeclarationList()!!)
                    {
                        declarations.put(nameDeclaration?.getText()!!, nameDeclaration!!)
                    }
                }
        }
        if (current is DataDeclaration) {
            val dataDeclaration : DataDeclaration = (current as DataDeclaration)
            for (binding : Binding? in dataDeclaration.getBindings()!!.getBindingList()) {
                for (declaration : NameDeclaration? in binding?.getNameDeclarationList()!!) {
                    declarations.put(declaration?.getText()!!, declaration!!)
                }
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
        if (parent is ForallExpression) {

            val bindingsList = parent.getTypedUntypedBindingList()

            for (binding in bindingsList) {
              if (parent.getExpression() != element && bindingsList.indexOf(element) <= bindingsList.indexOf(binding)) {
                  continue;
              }
              for (nameDeclaration in binding!!.getNameDeclarationList()) {
                declarations.put(nameDeclaration!!.getText()!!, nameDeclaration)

                val typeSignature = binding.getTypeSignature()
                if (typeSignature != null) {
                    for (nameDeclaration in typeSignature.getNameDeclarationList()) {
                        declarations.put(nameDeclaration!!.getText()!!, nameDeclaration)
                    }
                }
              }
            }


        }
        if (parent is ModuleDeclaration) {
            addBindings(parent.getBindings()!!.getBindingList().map { it!! }, declarations)
            if (parent.getChildren().toList().contains(element)) {
                val moduleDeclaration = AgdaModuleScope(parent).getVisibleDeclarations()
                declarations.putAll(moduleDeclaration);
            }
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

}

