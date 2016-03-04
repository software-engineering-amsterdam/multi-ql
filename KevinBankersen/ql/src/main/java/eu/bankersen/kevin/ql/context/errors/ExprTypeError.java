package eu.bankersen.kevin.ql.context.errors;

import eu.bankersen.kevin.ql.ast.type.Type;

public class ExprTypeError extends TypeCheckingError {


    public ExprTypeError(int line, Type lhs, Type rhs, Type expr) {
	super(line, "Expected " + expr + " got " + lhs + " and " + rhs + "!");
    }
    
    public ExprTypeError(int line, Type lhs, Type rhs) {
	super(line, "Expected " + lhs + " got " + rhs + "!");
    }
}