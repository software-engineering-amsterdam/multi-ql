package org.uva.sea.ql.evalutor;

import org.joda.money.Money;

public class MoneyValue extends Value {
	private final Money value;

	public MoneyValue(Money money) {
		this.value = money;
	}

	@Override
	public Money getMoneyValue() {
		return this.value;
	}

	@Override
	public Value add(Value val) {
		return val.addMoney(this);
	}

	@Override
	public Value addMoney(MoneyValue money) {
		return new MoneyValue(money.value.plus(this.value));
	}

	@Override
	public Value sub(Value money) {
		return money.subMoney(this);
	}

	@Override
	public Value subMoney(MoneyValue money) {
		return new MoneyValue(money.value.minus(this.value));
	}

	@Override
	public Value greaterThan(Value bool) {
		return bool.greaterThanMoney(this);
	}

	@Override
	public Value greaterThanMoney(MoneyValue money) {
		return new BooleanValue(money.value.isGreaterThan(this.value));
	}

	@Override
	public Value greaterOrEqual(Value value) {
		return value.greaterOrEqualMoney(this);
	}

	@Override
	public Value greaterOrEqualMoney(MoneyValue money) {
		return new BooleanValue(money.value.isEqual(this.value) || money.value.isGreaterThan(this.value));
	}

	@Override
	public Value lessThan(Value value) {
		return value.lessthanMoney(this);
	}

	@Override
	public Value lessthanMoney(MoneyValue money) {
		return new BooleanValue(money.value.isLessThan(this.value));
	}

	@Override
	public Value lessOrEqual(Value value) {
		return value.lessOrEqualMoney(this);
	}

	@Override
	public Value lessOrEqualMoney(MoneyValue money) {
		return new BooleanValue(money.value.isEqual(this.value) || money.value.isLessThan(this.value));
	}

	@Override
	public Value equal(Value value) {
		return value.equalMoney(this);
	}

	@Override
	public Value equalMoney(MoneyValue money) {
		return new BooleanValue(money.value.isEqual(this.value));
	}

	@Override
	public Value notEqual(Value value) {
		return value.notEqualMoney(this);
	}

	@Override
	public Value notEqualMoney(MoneyValue money) {
		return new BooleanValue(!money.value.isEqual(this.value));
	}

	@Override
	public Value negative() {
		return new MoneyValue(value.negated());
	}

	@Override
	public Value positive() {
		return new MoneyValue(value.abs());
	}

	@Override
	public String toString() {
		return value.getAmount().toString();
	}

}
