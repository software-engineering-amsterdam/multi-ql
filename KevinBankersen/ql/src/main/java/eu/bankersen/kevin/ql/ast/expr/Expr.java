package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public abstract class Expr {
    
    private final Type type;
    
    public Expr(Type type) {
	this.type = type;
    }
    
    public Type getType(Context context) {
	return type;
    }

    public abstract Object eval(SymbolTable symbolTable) throws EvaluateExeption;

    public abstract Context checkType(Context context);
}