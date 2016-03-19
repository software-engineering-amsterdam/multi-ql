package org.uva.sea.ql.evalutor;

import java.math.RoundingMode;

public class IntegerValue extends Value {
	private final int value;
	public IntegerValue(int integer){
		this.value = integer;
	}
	
	@Override
	public Integer getIntegerValue(){
		return this.value;
	}

	@Override
	public Value positive() {
		return new IntegerValue(Math.abs(value));
	}

	@Override
	public Value negative() {
		return new IntegerValue(Math.negateExact(value));
	}

	@Override
	public Value add(Value value) {
		return value.addInteger(this);
	}

	@Override
	public Value addInteger(IntegerValue integer) {
		return new IntegerValue(integer.value + this.value);
	}

	@Override
	public Value addMoney(MoneyValue money) {
		return new MoneyValue(money.getMoneyValue().plus(this.value, RoundingMode.DOWN));
	}

	@Override
	public Value sub(Value value) {
		return value.subInteger(this);
	}

	@Override
	public Value subInteger(IntegerValue integer) {
		return new IntegerValue(integer.value - this.value);
	}

	@Override
	public Value subMoney(MoneyValue money) {
		return new MoneyValue(money.getMoneyValue().minus(this.value));
	}

	@Override
	public Value div(Value value) {
		return value.divInteger(this);
	}

	@Override
	public Value divInteger(IntegerValue integer) {
		return new IntegerValue(integer.value / this.value);
	}

	@Override
	public Value divMoney(MoneyValue money) {
		return new MoneyValue(money.getMoneyValue().dividedBy(this.value, RoundingMode.DOWN));
	}

	@Override
	public Value mul(Value value) {
		return value.mulInteger(this);
	}

	@Override
	public Value mulInteger(IntegerValue integer) {
		return new IntegerValue(integer.value * this.value);
	}

	
	@Override
	public Value mulMoney(MoneyValue money) {
		return new MoneyValue(money.getMoneyValue().multipliedBy(this.value));
	}

	@Override
	public Value notEqual(Value value) {
		return value.notEqualInteger(this);
	}

	@Override
	public Value notEqualInteger(IntegerValue integer) {
		return new BooleanValue(integer.value != this.value);
	}

	@Override
	public Value lessThan(Value value) {
		return value.lessthanInteger(this);
	}

	@Override
	public Value lessthanInteger(IntegerValue integer) {
		return new BooleanValue(integer.value < this.value);
	}

	@Override
	public Value lessOrEqual(Value value) {
		return value.lessOrEqualInteger(this);
	}

	@Override
	public Value lessOrEqualInteger(IntegerValue integer) {
		return new BooleanValue(integer.value <= this.value);
	}

	@Override
	public Value greaterThan(Value value) {
		return value.greaterThanInteger(this);
	}

	@Override
	public Value greaterThanInteger(IntegerValue integer) {
		return new BooleanValue(integer.value > this.value);
	}

	@Override
	public Value greaterOrEqual(Value value) {
		return value.greaterOrEqualInteger(this);
	}

	@Override
	public Value greaterOrEqualInteger(IntegerValue integer) {
		return new BooleanValue(integer.value >= this.value);
	}
		


}
