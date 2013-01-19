package org.jetbrains.agda.psi

import org.jetbrains.agda.mixfix.Grammar
import org.jetbrains.agda.mixfix.ApplicationTreeElement
import org.jetbrains.agda.mixfix.OperationElement
import org.jetbrains.agda.scope.AgdaExpressionScope


fun findTypeDeclaration(function : FunctionDeclaration) : FunctionTypeDeclaration {
    val expression = function.getLhs().getExpression()
    if (expression is Application) {
        val treeElement = Grammar.parse(expression)
        if (treeElement is OperationElement) {
            return treeElement.declaration as FunctionTypeDeclaration
        }
        throw RuntimeException();
    }
    if (expression is Expression) {
        val fqName = expression.getFqName()
        if (fqName != null) {
            return fqName.getReference()!!.resolve()!! as FunctionTypeDeclaration
        }
    }
    throw RuntimeException();

}