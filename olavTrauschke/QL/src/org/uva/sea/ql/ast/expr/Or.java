package org.uva.sea.ql.ast.expr;

public class Or extends BooleanExpr {
    
    private BooleanExpr firstExpr;
    private BooleanExpr secondExpr;
    
    public Or(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (BooleanExpr) theFirstExpr;
        secondExpr = (BooleanExpr) theSecondExpr;
    }
    
}
