package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;

public class Literal extends Expr {

    private final QLType type;
    private final QLValue value;

    public Literal(int line, QLValue value, QLType type) {
	super(line);
	this.type = type;
	this.value = value;
    }

    public QLType type() {
	return type;
    }

    public QLValue value() {
	return value;
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
