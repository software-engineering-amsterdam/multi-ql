package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.errors.ExprTypeError;

public abstract class IntegerExpr extends Expr {

    private final Expr lhs;
    private final Expr rhs;
    private final int line;

    public IntegerExpr(Expr lhs, Expr rhs, int line) {
	super(Type.INTEGER);
	this.lhs = lhs;
	this.rhs = rhs;
	this.line = line;
    }

    public Expr lhs() {
	return lhs;
    }

    public Expr rhs() {
	return rhs;
    }

    public abstract Integer eval(SymbolTable symbolTable) throws EvaluateExeption;

    @Override
    public Context checkType(Context context) {
	context = lhs.checkType(context);
	context = rhs.checkType(context);

	if (!equalExpr(lhs.getType(context), rhs.getType(context))) {    
	    context.addError(new ExprTypeError(
		    line,
		    lhs.getType(context),
		    rhs.getType(context),
		    getType(context))
		    );
	}
	return context;
    }

    private boolean equalExpr(Type lhs, Type rhs) {
	return lhs.equals(rhs) && lhs.equals(Type.INTEGER);
    }
}
