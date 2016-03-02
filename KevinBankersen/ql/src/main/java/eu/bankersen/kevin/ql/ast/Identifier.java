package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.errors.NotDeclaredError;
import eu.bankersen.kevin.ql.context.errors.OutOfScopeError;


public class Identifier extends Expr {

    private final String name;
    private final int line;

    public Identifier(String name, int line) {
	super(Type.UNDIFINED);
	this.name = name;
	this.line = line;
    }

    @Override
    public Object eval(SymbolTable symbolTable) throws EvaluateExeption {

	Object value = symbolTable.getSymbol(name).getValue();

	if (value != Type.EMPTY) {
	    return value;   
	} else {
	    throw new EvaluateExeption();
	} 
    }

    @Override
    public Context checkType(Context context) {
	if (context.checkID(name)) {
	    if (!context.getSymbol(name).getActive()) {
		context.addError(new OutOfScopeError(line, name));	 
	    }
	} else {
	    context.addError(new NotDeclaredError(line, name));	    
	}
	return context;
    }

    @Override
    public Type getType(Context context) {
	return context.getSymbol(name).getType();
    }
}
