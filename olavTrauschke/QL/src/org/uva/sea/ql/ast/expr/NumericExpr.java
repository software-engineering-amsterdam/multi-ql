package org.uva.sea.ql.ast.expr;

public abstract class NumericExpr extends Expr {
    
    public static final String NON_NUMERIC_OPERANDS_MESSAGE = "Can not perform numeric operation on non-numeric operands";
    
    public NumericExpr() {
        super(false, true, false);
    }
    
    protected void throwNonNumericOperandsException() {
        throw new IllegalArgumentException(NON_NUMERIC_OPERANDS_MESSAGE);
    }
}
