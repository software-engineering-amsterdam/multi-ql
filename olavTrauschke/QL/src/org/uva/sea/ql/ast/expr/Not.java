package org.uva.sea.ql.ast.expr;

public class Not extends BooleanExpr {
    
    private BooleanExpr content;
    
    public Not(Expr theContent) {
        content = (BooleanExpr) theContent;
    }
    
}
