package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.Expr;

public class Literal extends Expr {

    private final Object value;
    private final Type type;
    
    public Literal(final Object value, final Type type) {
	this.value = value;
	this.type = type;
    }
    
    @Override
    public final Object eval() {
	return value; 
    }

    @Override
    public final void checkType() {
    }

    @Override
    public final Type getType() {
	return type;
    }
    
    @Override
    public final String toString() {
	return value.toString();
    }
    
}
