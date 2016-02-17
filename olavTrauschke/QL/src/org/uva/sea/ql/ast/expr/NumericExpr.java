package org.uva.sea.ql.ast.expr;

public abstract class NumericExpr extends Expr {
    
    public static final String TYPE_ERROR_MESSAGE = "Can not perform numeric operation on non-numeric operands";
    
    public NumericExpr() {
        super(false, true, false);
    }
}
