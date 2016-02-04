package org.uva.sea.ql.ast.expr;

public class Add extends NumericExpr {
    
    private NumericExpr firstExpr;
    private NumericExpr secondExpr;
    
    public Add(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (NumericExpr) theFirstExpr;
        secondExpr = (NumericExpr) theSecondExpr;
    }
}
