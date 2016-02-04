package org.uva.sea.ql.ast.expr;


public abstract class ComparisonExpr extends BooleanExpr {
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public ComparisonExpr(Expr theFirstExpr, Expr theSecondExpr) {
        if ((theFirstExpr instanceof BooleanExpr && theSecondExpr instanceof BooleanExpr)
                || (theFirstExpr instanceof NumericExpr && theSecondExpr instanceof NumericExpr)) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
        }
        else {
            throw new IllegalArgumentException("The arguments of a comparison should be of the same type.");
        }
    }
}
