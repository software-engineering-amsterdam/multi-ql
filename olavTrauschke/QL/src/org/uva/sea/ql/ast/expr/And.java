package org.uva.sea.ql.ast.expr;

public class And extends BooleanConjunctiveExpr {
    
    public And(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
