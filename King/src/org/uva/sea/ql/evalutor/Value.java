package org.uva.sea.ql.evalutor;

public abstract class Value {
	
	public Value not() {
		throw new UnsupportedOperationException();
	}
	
	public Value positive() {
		throw new UnsupportedOperationException();
	}
	
	public Value negative() {
		throw new UnsupportedOperationException();
	}
	
	public Value add(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value addInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value addMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value addString(StringValue str) {
		throw new UnsupportedOperationException();
	}
	
	public Value sub(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value subInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value subMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value div(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value divInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value divMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value mul(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value mulInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value mulMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value and(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value andBoolean(BooleanValue bool) {
		throw new UnsupportedOperationException();
	}
	
	public Value or(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value orBoolean(BooleanValue bool) {
		throw new UnsupportedOperationException();
	}
	
	public Value equal(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value equalInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value equalMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value equalString(StringValue str) {
		throw new UnsupportedOperationException();
	}
	
	public Value equalBoolean(BooleanValue bool) {
		throw new UnsupportedOperationException();
	}
	public Value notEqual(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value notEqualInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value notEqualMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value notEqualString(StringValue str) {
		throw new UnsupportedOperationException();
	}
	
	public Value notEqualBoolean(BooleanValue bool) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessThan(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessthanInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessthanMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessOrEqual(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessOrEqualInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value lessOrEqualMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterThan(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterThanInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterThanMoney(MoneyValue momey) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterOrEqual(Value value) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterOrEqualInteger(IntegerValue integer) {
		throw new UnsupportedOperationException();
	}
	
	public Value greaterOrEqualMoney(MoneyValue money) {
		throw new UnsupportedOperationException();
	}
}
