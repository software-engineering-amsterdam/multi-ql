package org.uva.sea.ql.ast.expr;

public class And extends BooleanExpr {
    
    private BooleanExpr firstExpr;
    private BooleanExpr secondExpr;
    
    public And(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (BooleanExpr) theFirstExpr;
        secondExpr = (BooleanExpr) theSecondExpr;
    }
    
}
