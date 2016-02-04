package org.uva.sea.ql.ast.expr;

public class Int extends NumericExpr {
    
    private int content;
    
    public Int(int theContent) {
        content = theContent;
    }
}
