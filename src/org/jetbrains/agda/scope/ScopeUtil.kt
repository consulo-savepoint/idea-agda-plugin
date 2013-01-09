package org.jetbrains.agda.scope

import org.jetbrains.agda.psi.NameDeclaration
import com.intellij.psi.PsiElement

fun <A : Any> ListBang(list : List<A?>): List<A> {
    return list.map({ it!! })
}

fun NameDeclaration.getPair() =  Pair<String, PsiElement>(this.getText()!!, this)