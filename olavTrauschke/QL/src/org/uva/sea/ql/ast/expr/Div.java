package org.uva.sea.ql.ast.expr;

public class Div extends NumericExpr {
    
    private NumericExpr firstExpr;
    private NumericExpr secondExpr;
    
    public Div(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (NumericExpr) theFirstExpr;
        secondExpr = (NumericExpr) theSecondExpr;
    }
}
