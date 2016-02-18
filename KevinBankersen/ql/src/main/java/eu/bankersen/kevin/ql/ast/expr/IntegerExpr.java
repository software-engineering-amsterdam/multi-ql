package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class IntegerExpr extends Expr {

    private final Type type = Type.INTEGER;

    protected Expr lhs;
    protected Expr rhs;
    protected int line;

    public abstract Integer eval();

    @Override
    public final void checkType() {
	lhs.checkType();
	rhs.checkType();

	Boolean check = lhs.getType().equals(type) && rhs.getType().equals(type);

	if (!check) {
	    super.context.addError("TYPE_ERROR @Line " + line 
		    + ": expected " + type
		    + " got " + lhs.getType() 
		    + " and " + rhs.getType() 
		    );
	}
    }

    @Override
    public final Type getType() {
	return type;
    }

}
