package org.jetbrains.agda.scope

/**
 * @author Evgeny.Kurbatsky
 */
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.agda.psi.FunctionDeclaration
import org.jetbrains.agda.psi.TeleArrow
import org.jetbrains.agda.mixfix.Grammar
import org.jetbrains.agda.psi.TypeSignature
import org.jetbrains.agda.psi.ImplicitTelescope
import org.jetbrains.agda.psi.Telescope
import org.jetbrains.agda.psi.ExplicitTelescope
import org.jetbrains.agda.psi.ForallExpression
import org.jetbrains.agda.psi.LambdaExpression
import org.jetbrains.agda.psi.NameDeclaration
import org.jetbrains.agda.psi.TypedUntypedBinding
import org.jetbrains.agda.psi.DataDeclaration
import org.jetbrains.agda.psi.Binding
import org.jetbrains.agda.psi.LetExpression
import java.util.HashMap
import org.jetbrains.agda.psi.RecordDeclaration
import org.jetbrains.agda.psi.FqName
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration
import org.jetbrains.agda.psi.RecordField
import org.jetbrains.agda.psi.TypeSignatures
import org.jetbrains.agda.psi.WhereEpression


public class AgdaExpressionScope(val element : PsiElement) {

    public fun getVisibleDeclarations() : MutableMap<String, PsiElement> {

        if (element is PsiFile) {
            return AgdaModuleScope(element).getDeclarations();
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
                var dataDeclaration : DataDeclaration? = (current as DataDeclaration?)
                for (binding : Binding? in dataDeclaration?.getBindingList()!!)
                {
                    for (declaration : NameDeclaration? in binding?.getNameDeclarationList()!!)
                    {
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
            addFunctionParameters(declaration.getLhs().getExpression(), declarations, Grammar.getOperationParts(declarations as Map<String?, PsiElement?>))
        }

        if (parent is TypeSignatures) {
            val typeSignatures = parent.getTypeSignatureList();
            for (typeSignature in typeSignatures) {
                if (typeSignature == element) {
                    break;
                }
                for (nameDeclaration in typeSignature!!.getNameDeclarationList()) {
                    declarations.put(nameDeclaration!!.getText()!!, nameDeclaration)
                }
            }
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
        return declarations
    }
    private fun addFunctionParameters(expression : PsiElement, declarations : MutableMap<String, PsiElement>, operationParts : Set<String?>?) : Unit {
        if (expression is FqName) {
            var text : String? = expression.getText();
            if (!declarations.containsKey(text) && !operationParts!!.contains(text))
            {
                declarations.put(text!!, expression)
            }

        }
        else
        {
            for (child : PsiElement? in expression.getChildren())
            {
                addFunctionParameters(child!!, declarations, operationParts)
            }
        }
    }

}

