package ql.ast.value;

import ql.ast.visitor.Visitor;

public class IntegerValue extends Value{
	private final Integer value;
	
	public IntegerValue(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public BooleanValue Eq(IntegerValue integerValue) {
		return new BooleanValue(value == integerValue.getValue());
	}

	@Override
	public BooleanValue Geq(IntegerValue integerValue) {
		return new BooleanValue(value >= integerValue.getValue());
	}

	@Override
	public BooleanValue Gt(IntegerValue integerValue) {
		return new BooleanValue(value > integerValue.getValue());
	}

	@Override
	public BooleanValue LEq(IntegerValue integerValue) {
		return new BooleanValue(value <= integerValue.getValue());
	}

	@Override
	public BooleanValue LT(IntegerValue integerValue) {
		return new BooleanValue(value < integerValue.getValue());
	}

	@Override
	public BooleanValue NEq(IntegerValue integerValue) {
		return new BooleanValue(value != integerValue.getValue());
	}

	@Override
	public IntegerValue add(IntegerValue integerValue) {
		return new IntegerValue(value + integerValue.getValue());
	}

	@Override
	public IntegerValue Sub(IntegerValue integerValue) {
		return new IntegerValue(value - integerValue.getValue());
	}

	@Override
	public IntegerValue Mul(IntegerValue integerValue) {
		return new IntegerValue(value * integerValue.getValue());
	}

	@Override
	public IntegerValue Div(IntegerValue integerValue) {
		return new IntegerValue(value / integerValue.getValue());
	}

	@Override
	public IntegerValue Pos() {
		return new IntegerValue(Math.abs(value));
	}

	@Override
	public IntegerValue Neg() {
		return new IntegerValue(-Math.abs(value));
	}
}
