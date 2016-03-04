package eu.bankersen.kevin.ql.ast;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.SymbolTableBuilder;
import eu.bankersen.kevin.ql.context.errors.AllreadyDeclaredError;
import eu.bankersen.kevin.ql.context.errors.ExprTypeError;
import eu.bankersen.kevin.ql.oldcode.QLVisitor;

public class Variable {

    private final String name;
    private final Type type;
    private final int line;
    private final Expr expr;

    public Variable(String name, Type type, Expr expr, int line) {
	this.name = name;
	this.type = type;
	this.expr = expr;
	this.line = line;
    }

    public Type getType() {
	return type;
    }
    
    public Expr expr() {
	return expr;
    }
    
    public int line() {
	return line;
    }
    
    public String getName() {
	return name;
    }

    public Context checkType(Context context, String text, Boolean computed) {

	// Refactor this stuff out..
	if (context.checkID(name)) {
	    context.addError(new AllreadyDeclaredError(line, name));
	} else {
	    context.addSymbol(computed, name, text, type, null);
	}
	
	return context.evaluate(this);
    }

    public SymbolTable eval(SymbolTable symbolTable) {

	try {
	    Object value = expr.eval(symbolTable);
	    symbolTable.updateSymbol(name, value);
	} catch (EvaluateExeption  e) {
	    Log.debug("Cannot evaluate expression yet");
	}
	return symbolTable;
    }
}
