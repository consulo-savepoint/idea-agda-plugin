package org.jetbrains.agda.core

import com.intellij.psi.PsiElement
import java.util.LinkedHashMap
import java.util.HashMap
import org.jetbrains.agda.psi.DataDeclaration
import com.intellij.psi.PsiFile
import org.jetbrains.agda.psi.Expression
import org.jetbrains.agda.psi.FunctionDeclaration
import org.jetbrains.agda.psi.Application
import org.jetbrains.agda.mixfix.TreeElement
import org.jetbrains.agda.mixfix.Grammar
import java.util.Arrays
import org.codehaus.groovy.ast.expr.ExpressionTransformer
import java.util.ArrayList
import java.nio.charset.CoderResult
import org.jetbrains.agda.core.expression.CAnything
import org.jetbrains.agda.core.expression.CMetaRef
import org.jetbrains.agda.mixfix.ApplicationTreeElement
import org.jetbrains.agda.util.FList
import org.jetbrains.agda.util.singeFList
import org.jetbrains.agda.util.case
import org.jetbrains.agda.util.Nil
import org.jetbrains.agda.util.Cons

/**
 * @author Evgeny.Kurbatsky
 */
public open class TypeChecker(val declarationProvider : (Any) -> CDeclaration?) {

    public fun calculateType(expression : CExpression) : CExpression? {
        val signature = EquationsContainer()
        var result = checkType(emptyContext, signature, expression, null, false)

        for (rule in signature.rules) {
            result = replaceVar(result, rule.name, rule.value)
        }

        return result;
    }

    public open fun checkTypes(body : CFunctionBody) : Unit {
        val signature = EquationsContainer()
        val resultType = checkType(emptyContext, signature, body.left, null, true)

        checkType(emptyContext, signature, body.right, resultType, false)
    }

    public fun checkType(context : CContext, signature : EquationsContainer, expression : CExpression,  aType : CExpression?, leftPart : Boolean) : CExpression {
        val result = doCheckType(context, signature, expression, aType, leftPart)
        if (aType != null) {
            unifyExpressions(context, signature, result, aType);
        }
        expression.setType(result)
        return result;
    }

    fun replaceVar(expr : CExpression, name : String, value : CExpression) =
        expr.transform(object : ExpressionTransformer() {

            public override fun transformRef(ref: CRefExpression): CExpression {
                if (ref.name == name) {
                    return value;
                } else {
                    return super<ExpressionTransformer>.transformRef(ref)
                }
            }

            public override fun transformMetaRef(ref: CMetaRef): CExpression {
                if (ref.name == name) {
                    return value;
                } else {
                    return super<ExpressionTransformer>.transformMetaRef(ref)
                }
            }
        })


    private fun unifyExpressions(context : CContext, signature : EquationsContainer, left : CExpression, right : CExpression) {
        if (left is CRefExpression) {
            if (right is CRefExpression) {
                if (left.declaration == right.declaration) {
                    return
                }
            }
        }
        if (left is CMetaRef) {
            if (left != right) {
                signature.rules.add(Rule(left.name, right))
            }
            return
        }
        if (right is CMetaRef) {
            if (left != right) {
                signature.rules.add(Rule(right.name, left))
            }
            return
        }
        if (left is CImplicitArrowExpression) {
            val newLeft = replaceVar(left.right, left.name, signature.nextVarRef(left.name, left.left))
            return unifyExpressions(context, signature, newLeft, right);
        }
        if (right is CImplicitArrowExpression) {
            val newRight = replaceVar(right.right, right.name, signature.nextVarRef(right.name, right.left))
            return unifyExpressions(context, signature, left, newRight)
        }
        if (left is CApplication) {
            if (right is CApplication) {
                unifyExpressions(context, signature, left.left,  right.left)
                unifyExpressions(context, signature, left.right, right.right)
                return
            }
        }
        throw RuntimeException();
    }


    private fun doCheckApplication(context : CContext, left : CExpression, rightExpression : CExpression, signature : EquationsContainer, leftPart : Boolean) : CExpression {
        if (left is CArrowExpression) {
            checkType(context, signature, rightExpression, left.left, leftPart)
            return left.right;
        } else if (left is CPiArrowExpression) {
            val piArrowExpression : CPiArrowExpression = left;
            throw RuntimeException()
        } else if (left is CImplicitArrowExpression) {
            val expression = replaceVar(left.right, left.name, signature.nextVarRef(left.name, left.left))
            return doCheckApplication(context, expression, rightExpression, signature, leftPart);
        }
        throw RuntimeException()
    }

    private fun doCheckType(context : CContext, signature : EquationsContainer, expression : CExpression, aType : CExpression?, leftPart : Boolean) : CExpression {
        if (expression is CApplication) {
            val application : CApplication = expression
            val leftType : CExpression = checkType(context, signature, application.left, null, leftPart)
            return doCheckApplication(context, leftType, application.right, signature, leftPart);
        } else if (expression is CRefExpression) {
            val contextContent = context.get(expression.name)
            if (contextContent != null) {
                return contextContent
            }
            val functionsVar = signature.variables.get(expression.name)
            if (functionsVar != null) {
                return functionsVar
            }

            val cDeclaration : CDeclaration? = declarationProvider(expression.declaration)
            if (cDeclaration != null) {
                return cDeclaration.aType
            }

            if (leftPart) {
                signature.variables.put(expression.name, aType!!)
                return aType
            }
            throw RuntimeException()
        }
        if (expression is CAnything) {
            return aType!!;
        }
        throw RuntimeException()
    }

    fun printDebug() : Unit {

    }

    fun normalize(expr : CExpression) : CExpression {
        if (expr is CRefExpression) {
            val declaration = declarationProvider(expr.declaration)
            if (declaration != null) {
                if (declaration is CFunctionDeclaration) {
                    if (declaration.bodies.size == 1) {
                        return normalize(declaration.bodies[0].right)
                    }
                }
            }
        }
        if (expr is CApplication) {
            val application : CApplication = expr
            return apply(normalize(application.left), singeFList(normalize(application.right)))
        }
        return expr;
    }

    fun matchOne(pattern : CExpression, arg : CExpression) : FList<Pair<String, CExpression>>? {
        return null;
    }

    fun match(patterns : FList<CExpression>, args : FList<CExpression>) : FList<Pair<String, CExpression>>? =
        when (patterns) {
            is Nil<CExpression> -> Nil()
            is Cons<CExpression> -> when (args) {
                is Nil<CExpression> -> null
                is Cons<CExpression> -> {
                    val m1 = matchOne(patterns.head, args.head)
                    val m2 = match(patterns.tail, args.tail)
                    if (m1 == null ||m2 == null) {
                        null
                    }else {
                        m2
                    }
                }
                else -> null
            }
            else -> null
        }


    fun reduce(f : CFunctionDeclaration, args : FList<CExpression>) : CExpression {
        for (body in f.bodies) {
            val m = match(getArguments(body.left), args)
            if (m != null) {
                throw UnsupportedOperationException()
            }
        }
        throw UnsupportedOperationException()
    }

    fun getArguments(expression : CExpression, acc : FList<CExpression> = Nil()) : FList<CExpression> =
            when (expression) {
                is CApplication -> getArguments(expression.left, Cons(expression.right, acc))

                else            -> acc
            }


    fun apply(f : CExpression, args : FList<CExpression>) : CExpression =
      when (f) {
        is CRefExpression -> {
          val declaration = declarationProvider(f.declaration)
          when (declaration) {
            is CFunctionDeclaration -> {
              reduce(declaration, args)
            }
            else -> {
              throw RuntimeException()
            }
          }
        }
        else -> {
          throw RuntimeException();
        }
      }

}
