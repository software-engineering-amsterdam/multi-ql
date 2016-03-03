package org.uva.sea.ql.evaluator;

public abstract class Value {

	
	
	public Value not() {
		return new UndefinedValue();
	}
	
	public Value positive() {
		return new UndefinedValue();
	}
	
	public Value negative() {
		return new UndefinedValue();
	}
	
	public Value add(Value value) {
		return new UndefinedValue();
	}
	
	public Value sub(Value value) {
		return new UndefinedValue();
	}
	
	public Value div(Value value) {
		return new UndefinedValue();
	}
	
	public Value mul(Value value) {
		return new UndefinedValue();
	}
	
	public Value and(Value value) {
		return new UndefinedValue();
	}
	
	public Value or(Value value) {
		return new UndefinedValue();
	}
	
	public Value equal(Value value) {
		return new UndefinedValue();
	}
	
	public Value notEqual(Value value) {
		return new UndefinedValue();
	}
	
	public Value less(Value value) {
		return new UndefinedValue();
	}
	
	public Value lessOrEqual(Value value) {
		return new UndefinedValue();
	}
	
	public Value greater(Value value) {
		return new UndefinedValue();
	}
	
	public Value greaterOrEual(Value value) {
		return new UndefinedValue();
	}
	
}