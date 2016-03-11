package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Literal extends Expr {

    private final QLType type;
    private final QLValue value;

    public Literal(int line, QLValue value, QLType type) {
	super(line);
	this.type = type;
	this.value = value;
    }

    @Override
    public QLValue eval(Environment context) {
	return value;
    }

    public QLType getType() {
	return type;
    }

    public QLValue getValue() {
	return value;
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }
}
