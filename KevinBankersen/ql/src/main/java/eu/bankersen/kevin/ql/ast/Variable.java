package eu.bankersen.kevin.ql.ast;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.errors.AllreadyDeclaredError;
import eu.bankersen.kevin.ql.context.errors.ExprTypeError;

public class Variable {

    private final String name;
    private final Type type;
    private final int line;
    private final Expr expr;
    private Object value;

    public Variable(String name, Type type, Expr expr, int line) {
	this.name = name;
	this.type = type;
	this.expr = expr;
	this.line = line;
	this.value = Type.EMPTY;
    }

    public String getName() {
	return name;
    }

    public Type getType() {
	return type;
    }

    public Context checkType(Context context, String text) {

	if (context.checkID(name)) {
	    context.addError(new AllreadyDeclaredError(line, name));
	} else {
	    context.addSymbol(name, text, type, value);
	}

	context = expr.checkType(context);
	
	if (!expr.getType(context).equals(type)) {
	    context.addError(new ExprTypeError(line, getType(), expr.getType(context)));
	}
	return context;
    }

    public Object getValue() {
	return value;
    }

    public String toString() {
	return this.getName() + ": " + this.getType() + "=" + this.getValue();
    }

    public SymbolTable eval(SymbolTable symbolTable) {

	try {
	    value = expr.eval(symbolTable);
	    symbolTable.updateSymbol(name, value);
	} catch (EvaluateExeption  e) {
	    Log.debug("Cannot evaluate expression yet");
	}
	return symbolTable;
    }
}
