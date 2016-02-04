package org.uva.sea.ql.ast.expr;

public class Mul extends NumericExpr {
    
    private NumericExpr firstExpr;
    private NumericExpr secondExpr;
    
    public Mul(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (NumericExpr) theFirstExpr;
        secondExpr = (NumericExpr) theSecondExpr;
    }
}
