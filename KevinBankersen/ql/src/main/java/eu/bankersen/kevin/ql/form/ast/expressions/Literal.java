package eu.bankersen.kevin.ql.form.ast.expressions;

import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.values.Value;

public class Literal extends Expression {

	private final Type type;
	private final Value value;

	public Literal(int line, Value value, Type type) {
		super(line);
		this.type = type;
		this.value = value;
	}

	public Type type() {
		return type;
	}

	public Value value() {
		return value;
	}

	@Override
	public <T, U> T accept(Visitor<T, U> v, U context) {
		return v.visit(this, context);
	}
}
