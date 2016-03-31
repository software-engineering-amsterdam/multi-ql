package ql.ast.value;

import ql.ast.visitor.Visitor;

public class BooleanValue extends Value{
	private final Boolean value;
	
	public BooleanValue(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public BooleanValue OrExpression(BooleanValue booleanValue) {
		return new BooleanValue(booleanValue.getValue() || value);
	}

	@Override
	public BooleanValue AndExpression(BooleanValue booleanValue) {
		return new BooleanValue(booleanValue.getValue() && value);
	}

	@Override
	public BooleanValue Not() {
		return new BooleanValue(!value);
	}
}
