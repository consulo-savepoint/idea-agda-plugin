package org.jetbrains.agda.core

/**
 * @author Evgeny.Kurbatsky
 */
open class ExpressionTransformer {

    public open fun transformApplication(application: CApplication) : CExpression {
        return CApplication(
                application.left.transform(this),
                application.right.transform(this));
    }

    public open fun transformArrow(arrow: CArrowExpression) : CExpression {
        return CArrowExpression(
                arrow.left.transform(this),
                arrow.right.transform(this));

    }

    public open fun transformImplicitArrow(implicitArrow : CImplicitArrowExpression) : CExpression {
        return CImplicitArrowExpression(
                implicitArrow.name,
                implicitArrow.left.transform(this),
                implicitArrow.right.transform(this))
    }

    public open fun transformPiArrow(piArrow : CPiArrowExpression) : CExpression {
        return CPiArrowExpression(
                piArrow.name,
                piArrow.left.transform(this),
                piArrow.right.transform(this))
    }

    public open fun transformRef(cref : CRefExpression) : CExpression {
        return cref;
    }

    public open fun transformSet(set : CSet) : CSet  {
        return set;
    }

}

fun CExpression.transform(transformer : ExpressionTransformer) : CExpression {
    when (this) {
        is CApplication -> return transformer.transformApplication(this);
        is CArrowExpression -> return transformer.transformArrow(this);
        is CImplicitArrowExpression -> return transformer.transformImplicitArrow(this);
        is CPiArrowExpression -> return transformer.transformPiArrow(this);
        is CRefExpression -> return transformer.transformRef(this);
        is CSet -> return transformer.transformSet(this);
        else -> throw RuntimeException();
    }
}