package nl.nicasso.ql.gui.evaluator.values;

public abstract class Value {

	public abstract Object getValue();

	public Value addition(Value arg) {
		return new UnknownValue();
	}

	public Value subtraction(Value arg) {
		return new UnknownValue();
	}

	public Value and(Value arg) {
		return new UnknownValue();
	}

	public Value or(Value arg) {
		return new UnknownValue();
	}

	public Value not() {
		return new UnknownValue();
	}

	public Value equal(Value arg) {
		return new UnknownValue();
	}

	public Value notEqual(Value arg) {
		return new UnknownValue();
	}

	public Value division(Value arg) {
		return new UnknownValue();
	}

	public Value multiplication(Value arg) {
		return new UnknownValue();
	}

	public Value greater(Value arg) {
		return new UnknownValue();
	}

	public Value greaterEqual(Value arg) {
		return new UnknownValue();
	}

	public Value less(Value arg) {
		return new UnknownValue();
	}

	public Value lessEqual(Value arg) {
		return new UnknownValue();
	}

	public Value multiplicationToInteger(IntegerValue v) {
		return new UnknownValue();
	}

	public Value divisionToInteger(IntegerValue v) {
		return new UnknownValue();
	}

	public Value multiplicationToMoney(MoneyValue v) {
		return new UnknownValue();
	}

	public Value divisionToMoney(MoneyValue v) {
		return new UnknownValue();
	}

}
