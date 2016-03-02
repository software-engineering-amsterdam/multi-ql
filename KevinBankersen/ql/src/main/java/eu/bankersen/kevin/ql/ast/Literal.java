package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Literal extends Expr {

    private final Object value;
    
    public Literal(Object value, Type type) {
	super(type);
	this.value = value;
    }
    
    @Override
    public Object eval(SymbolTable symbolTable) {
	return value; 
    }

    @Override
    public Context checkType(Context context) {
	return context;
    }
    
    @Override
    public String toString() {
	return value.toString();
    }
    
}
