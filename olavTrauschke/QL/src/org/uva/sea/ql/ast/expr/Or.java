package org.uva.sea.ql.ast.expr;

public class Or extends BooleanConjunctiveExpr {
    
    public Or(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
