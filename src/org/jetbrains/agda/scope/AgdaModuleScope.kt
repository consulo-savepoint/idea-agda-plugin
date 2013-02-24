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
import org.jetbrains.agda.psi.ImportName
import org.jetbrains.agda.psi.ModuleImport
import org.jetbrains.agda.psi.RecordDeclaration
import org.jetbrains.agda.psi.ModuleDeclaration
import org.jetbrains.agda.psi.WherePart
import org.jetbrains.agda.psi.Open
import com.intellij.codeInsight.daemon.impl.quickfix.AddReturnFix

/**
 * @author Evgeny.Kurbatsky
 */

class AgdaModuleScope(val module : PsiElement, val external : Boolean) : Scope() {

    public override fun traverse(function: (String, PsiElement) -> Boolean): Boolean {
        if (module is WherePart || module is ModuleDeclaration || module is AgdaFileImpl) {
            for (child in module.getChildren()) {
                when (child) {
                    is ModuleImport -> {
                        if (!external) {
                            if (traverseImport(child, function)) {
                                return true;
                            }
                        }
                    }
                    is Open -> {
                        val fqName = child.getFqName()
                        if (fqName != null) {
                            val text = fqName.getText()!!
                            val import = findImport(text);
                            if (import != null) {
                                if (traverseImport(import as ModuleImport, function)) {
                                    return true;
                                }
                            }
                        }
                    }
                    else -> {
                        val map = HashMap<String, PsiElement>()
                        getElementDeclarations(child!!, map)
                        for ((name, value) in map) {
                            if (function(name, value)) {
                                return true
                            }
                        }
                    }
                }
            }
            return false;
        }

        throw RuntimeException()
    }

    fun findImport(name : String) : ModuleImport? {
        for (child in module.getChildren()) {
            if (child is ModuleImport) {
                val asName = child.getAsName()
                if (asName != null && asName.getId().getText() == name) {
                    return child;
                }
            }
        }
        return null;
    }

    inline fun traverseImport(import : ModuleImport, function: (String, PsiElement) -> Boolean) : Boolean {
        val node = import.getNode()
        val fqName = import.getFqName()
        if (fqName != null) {
            val file = AgdaGlobalScope().find(fqName);
            if (file != null) {
                if (node!!.getFirstChildNode()!!.getText() == "open") {
                    return AgdaModuleScope(file, true).traverse(function)
                } else {
                    return AgdaModuleScope(file, true).traverse { key, value ->
                        function(fqName.getText() + "." + key, value)
                    }
                }
            }
        }
        return false;
    }

    private fun getElementDeclarations(element : PsiElement, map : MutableMap<String, PsiElement>) : Unit {
        if (element is DataDeclaration) {
            val dataDeclaration : DataDeclaration = element as DataDeclaration
            val nameDeclaration = dataDeclaration.getNameDeclaration()
            if (nameDeclaration != null) {
                map.putAll(nameDeclaration.getPair())
            }
            val constructors = dataDeclaration.getConstructors()
            if (constructors != null) {
                for (constructor : TypeSignature? in constructors.getTypeSignatureList()) {
                    for (nameDeclaration2 in constructor!!.getNameDeclarationList()) {
                        map.putAll(nameDeclaration2!!.getPair())
                    }
                }
            }
        }

        if (element is RecordDeclaration) {
            val nameDeclaration = element.getNameDeclaration()
            map.put(nameDeclaration!!.getText()!!, nameDeclaration)
        }

        if (element is FunctionTypeDeclaration) {
            val text = element.getFirstChild()!!.getText()!!
            map.put(text, element)
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
    }

}


public fun findDeclaration(element : AgdaReferenceElementImpl) : PsiElement? {

    if (element is FqNameImpl) {
        val text = element.getText()
        var declaration : PsiElement? = null
        AgdaExpressionScope(element).traverse { name, element ->
            if (name == text) {
                declaration = element
                true
            } else {
                false
            }
        }
        if (declaration != null) {
            return declaration;
        }
    }

    if (element.getParent() is Application) {
        var term : TreeElement? = Grammar.parse(element.getParent() as Application)
        if (term != null) {
            val declaration : PsiElement? = term?.getDeclaration(element)
            if (declaration != null) {
                return declaration
            }
        }

    }

    return null;
}