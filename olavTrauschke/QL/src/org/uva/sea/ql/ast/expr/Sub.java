package org.uva.sea.ql.ast.expr;

public class Sub extends NumericExpr {
    
    private NumericExpr firstExpr;
    private NumericExpr secondExpr;
    
    public Sub(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (NumericExpr) theFirstExpr;
        secondExpr = (NumericExpr) theSecondExpr;
    }
}
