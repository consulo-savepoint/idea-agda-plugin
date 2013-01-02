package org.jetbrains.agda.scope

import org.jetbrains.agda.psi.AgdaReferenceElementImpl
import com.intellij.psi.PsiElement
import org.jetbrains.agda.psi.Application
import org.jetbrains.agda.mixfix.Grammar
import org.jetbrains.agda.mixfix.TreeElement
import org.jetbrains.agda.psi.DataDeclaration
import org.jetbrains.agda.psi.Constructors
import org.jetbrains.agda.psi.TypeSignature
import org.jetbrains.agda.psi.NameDeclaration
import org.jetbrains.agda.psi.FunctionTypeDeclaration
import org.jetbrains.agda.psi.Pragma
import org.jetbrains.agda.psi.BuildInPragma
import org.jetbrains.agda.AgdaFileImpl
import com.intellij.psi.PsiFile
import com.intellij.util.containers.hash.HashMap
import org.jetbrains.agda.psi.Postulate
import org.jetbrains.agda.psi.FqName
import org.jetbrains.agda.psi.impl.FqNameImpl

/**
 * @author Evgeny.Kurbatsky
 */

class ModuleScope(val module : PsiElement) {

    public fun getDeclarations(map : MutableMap<String, PsiElement>) : Unit {
        getDeclarations(module, map)
    }

    private fun getDeclarations(element : PsiElement, map : MutableMap<String, PsiElement>) : Unit {
        if (element is DataDeclaration) {
            var dataDeclaration : DataDeclaration = element as DataDeclaration
            map.put(dataDeclaration.getNameDeclaration()!!.getText()!!, element)
            getDeclarations(dataDeclaration.getConstructors()!!, map)
        }

        if ((element is Constructors?))
        {
            var constructors = ((element as Constructors?))
            for (constructor : TypeSignature? in constructors?.getTypeSignatureList()!!)
            {
                for (nameDeclaration : NameDeclaration? in constructor?.getNameDeclarationList()!!)
                {
                    map.put(nameDeclaration?.getText()!!, nameDeclaration!!)
                }
            }
        }

        if ((element is FunctionTypeDeclaration?))
        {
            var text : String? = ((element as FunctionTypeDeclaration?))!!.getFirstChild()?.getText()
            map.put(text!!, element)
        }

        if (element is Postulate) {
            var postulate : Postulate = element as Postulate;
            var typeSygnatures = postulate.getPostulateBindings().getTypeSignatureList()
            for (typeSygnature in typeSygnatures) {
                for (name in typeSygnature!!.getNameDeclarationList()) {
                    map.put(name!!.getText()!!, typeSygnature.getExpression())
                }
            }

        }

        if ((element is AgdaFileImpl?))
        {
            for (child : PsiElement? in element.getChildren())
            {
                getDeclarations(child!!, map)
            }
        }

    }


}



public fun getGlobalDeclarations(element : PsiElement?) : MutableMap<String, PsiElement> {
    var root : PsiElement? = element
    while (!((root is PsiFile?)))
    {
        root = root?.getParent()
    }
    var map : MutableMap<String, PsiElement> = HashMap<String, PsiElement>()
    ModuleScope(root!!).getDeclarations(map)
    return map
}

public fun findDeclaration(element : AgdaReferenceElementImpl) : PsiElement? {
    if ((element.getParent() is Application?))
    {
        var term : TreeElement? = Grammar.parse((element.getParent() as Application?))
        if (term != null)
        {
            var declaration : PsiElement? = term?.getDeclaration(element)
            if (declaration != null)
            {
                return declaration
            }

        }

    }

    var declarations : Map<String, PsiElement> = AgdaExpressionScope(element).getVisibleDeclarations()
    if (element is FqNameImpl) {
        val text = element.getText()
        return declarations.get(text)
    }

    throw RuntimeException()
}