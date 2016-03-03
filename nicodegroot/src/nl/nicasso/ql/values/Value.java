package nl.nicasso.ql.values;

public abstract class Value {
	
	// THIS HAS TO GO, BUT CONFLICTS WITH THE DISPLAY SYMBOLTABLE
	public abstract Object getValue();
	
	public Value multiplication(MoneyValue arg) {
		return null;
	}
	
	public Value addition(Value arg) {
		return null;
	}
	
	public Value addToInt(IntegerValue v) {
		throw new AssertionError("KAPOT!");
	}
	
	public Value subtraction(Value arg) {
		return null;
	}
	
	public Value and(Value arg) {
		return null;
	}
	
	public Value or(Value arg) {
		return null;
	}
	
	public Value not() {
		return null;
	}
	
	public Value equal(Value arg) {
		return null;
	}
	
	public Value notEqual(Value arg) {
		return null;
	}
	
	public Value division(Value arg) {
		return null;
	}
	
	public Value multiplication(Value arg) {
		return null;
	}
	
	public Value greater(Value arg) {
		return null;
	}
	
	public Value greaterEqual(Value arg) {
		return null;
	}
	
	public Value less(Value arg) {
		return null;
	}
	
	public Value lessEqual(Value arg) {
		return null;
	}
	
}
