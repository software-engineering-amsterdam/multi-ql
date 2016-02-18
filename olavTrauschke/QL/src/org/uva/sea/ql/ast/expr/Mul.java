package org.uva.sea.ql.ast.expr;

public class Mul extends DoubleArgumentNumericExpr {
    
    public Mul(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
}
