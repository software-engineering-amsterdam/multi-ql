package eu.bankersen.kevin.ql.form.ast.expressions;

import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;

public class Identifier extends Expression {

	private final String name;

	public Identifier(String name, int line) {
		super(line);
		this.name = name;
	}

	public String name() {
		return name;
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}

}
