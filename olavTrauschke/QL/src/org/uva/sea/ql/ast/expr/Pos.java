package org.uva.sea.ql.ast.expr;

public class Pos extends NumericExpr {
    
    private NumericExpr content;
    
    public Pos(Expr theContent) {
        content = (NumericExpr) theContent;
    }
    
}
