package org.uva.sea.ql.ast.expr;

public class Ident extends Expr {
    
    private String content;
    
    public Ident(String theContent) {
        content = theContent;
    }
}
