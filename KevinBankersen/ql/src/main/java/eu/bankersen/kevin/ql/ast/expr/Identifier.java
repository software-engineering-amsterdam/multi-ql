package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Identifier extends Expr {

    private final String name;

    public Identifier(String name, int line) {
	super(line);
	this.name = name;
    }

    public String name() {
	return name;
    }

    @Override
    public QLValue eval(Environment context) {
	return context.getValue(name);
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
