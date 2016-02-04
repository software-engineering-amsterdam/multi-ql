package org.uva.sea.ql.ast.expr;

public class Neg extends NumericExpr {
    
    private NumericExpr content;
    
    public Neg(Expr theContent) {
        content = (NumericExpr) theContent;
    }
}
