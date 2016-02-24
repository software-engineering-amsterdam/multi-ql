export class PrimitiveValue {
	constructor(value) {
		this.value = value;
	}
	equal(otherValue) {
		return otherValue.rhsEqualPrimitive(this);
	}
	rhsEqualPrimitive(lhsPrimitiveValue) {
		return new BooleanValue(lhsPrimitiveValue.value === this.value);
	}
	notEqual(otherValue) {
		return otherValue.rhsNotEqualPrimitive(this);
	}
	rhsNotEqualPrimitive(lhsPrimitiveValue) {
		return new BooleanValue(lhsPrimitiveValue.value !== this.value);
	}
}

export class BooleanValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	not() {
		return new BooleanValue(!this.value);
	}
	and(otherValue) {
		otherValue.rhsAndBoolean(this);
	}
	rhsAndBoolean(lhsBooleanValue) {
		return new BooleanValue(lhsBooleanValue.value && this.value);
	}
	or(otherValue) {
		otherValue.rhsOrBoolean(this);
	}
	rhsOrBoolean(lhsBooleanValue) {
		return new BooleanValue(lhsBooleanValue.value || this.value);
	}
}

export class StringValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	add(otherValue) {
		return otherValue.rhsAddString(this);
	}
	rhsAddBoolean(lhsBooleanValue) {
		return new StringValue(lhsBooleanValue.value === true ? "true" : "value");
	}
	rhsAddString(lhsStringValue) {
		return new StringValue(lhsStringValue.value + this.value);
	}
	rhsAddInteger(lhsIntegerValue) {
		return new StringValue("" + lhsIntegerValue.value + this.value);
	}
	rhsAddFloat(lhsFloatValue) {
		return new StringValue("" + lhsFloatValue.value + this.value);
	}
	rhsAddMoney(lhsMoneyValue) {
		return new StringValue(); //TODO
	}
	subtract(otherValue) {
		return otherValue.rhsSubtractString(this);
	}
	multiply(otherValue) {
		return otherValue.rhsMultiplyString(this);
	}
	rhsMultiplyInteger(integerValue) {
		return new StringValue(this.value.repeat(integerValue.value));
	}
	divide(otherValue) {
		return otherValue.rhsDivideString(this);
	}
}

export class NumberValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	greater(otherValue) {
		return otherValue.rhsGreaterNumber(this);
	}
	rhsGreaterNumber(lhsNumberValue) {
		return new BooleanValue(lhsNumberValue.value > this.value);
	}
	greaterEqual(otherValue) {
		return otherValue.rhsGreaterEqualNumber(this);
	}
	rhsGreaterEqualNumber(lhsNumberValue) {
		return new BooleanValue(lhsNumberValue.value >= this.value);
	}
	less(otherValue) {
		return otherValue.rhsLessNumber(this);
	}
	rhsLessNumber(lhsNumberValue) {
		return new BooleanValue(lhsNumberValue.value < this.value);
	}
	lessEqual(otherValue) {
		return otherValue.rhsLessEqualNum(this);
	}
	rhsLessEqualNum(lhsNumberValue) {
		return new BooleanValue(lhsNumberValue.value <= this.value);
	}
}

export class IntegerValue {
	constructor(value) {
		this.value = value;
	}
	negate() {
		return new IntegerValue(-this.value);
	}
	add(otherValue) {
		return otherValue.rhsAddInteger(this);
	}
	rhsAddString(lhsStringValue) {
		return new StringValue("" + lhsStringValue.value + this.value);
	}
	rhsAddInteger(lhsIntegerValue) {
		return new IntegerValue(lhsIntegerValue.value + this.value);
	}
	rhsAddFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value + this.value);
	}
	subtract(otherValue) {
		return otherValue.rhsSubtractInteger(this);
	}
	rhsSubtractInteger(lhsIntegerValue) {
		return new IntegerValue(lhsIntegerValue.value - this.value);
	}
	rhsSubtractFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value - this.value);
	}
	multiply(otherValue) {
		return otherValue.rhsMultiplyInteger(this);
	}
	rhsMultiplyString(lhsStringValue) {
		return new StringValue(lhsStringValue.value.repeat(this.value));
	}
	rhsMultiplyInteger(lhsIntegerValue) {
		return new IntegerValue(lhsIntegerValue.value / this.value);
	}
	rhsMultiplyFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value / this.value);
	}
	rhsMultiplyMoney(lhsMoneyValue) {
		return new MoneyValue(); // TODO
	}
	divide(otherValue) {
		return otherValue.rhsDivideInteger(this);
	}
	rhsDivideInteger(lhsIntegerValue) {
		return new FloatValue(lhsIntegerValue.value / this.value);
	}
	rhsDivideFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value / this.value);
	}
	rhsDivideMoney(lhsMoneyValue) {
		return new MoneyValue(); // TODO
	}
}

export class FloatValue {
	constructor(value) {
		this.value = value;
	}
	negate() {
		return new FloatValue(-this.value);
	}
	add(otherValue) {
		return otherValue.rhsAddFloat(this);
	}
	rhsAddString(lhsStringValue) {
		return new StringValue("" + lhsStringValue.value + this.value);
	}
	rhsAddInteger(lhsIntegerValue) {
		return new FloatValue(lhsIntegerValue.value + this.value);
	}
	rhsAddFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value + this.value);
	}
	subtract(otherValue) {
		return otherValue.rhsSubtractFloat(this);
	}
	rhsSubtractInteger(lhsIntegerValue) {
		return new FloatValue(lhsIntegerValue.value - this.value);
	}
	rhsSubtractFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value - this.value);
	}
	multiply(otherValue) {
		return otherValue.rhsMultiplyFloat(this);
	}
	rhsMultiplyInteger(lhsIntegerValue) {
		return new FloatValue(lhsIntegerValue.value / this.value);
	}
	rhsMultiplyFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value / this.value);
	}
	rhsMultiplyMoney(lhsMoneyValue) {
		return new MoneyValue(); // TODO
	}
	divide(otherValue) {
		return otherValue.rhsDivideFloat(this);
	}
	rhsDivideInteger(lhsIntegerValue) {
		return new FloatValue(lhsIntegerValue.value / this.value);
	}
	rhsDivideFloat(lhsFloatValue) {
		return new FloatValue(lhsFloatValue.value / this.value);
	}
	rhsDivideMoney(lhsMoneyValue) {
		return new MoneyValue(); // TODO
	}

}

export class MoneyValue {
	constructor(euros, cents) {
		this.euros = euros;
		this.cents = cents;
	}
	negate() {
		return new MoneyValue();
	}
	add(otherValue) {
		return otherValue.rhsAddMoney(this);
	}
	rhsAddMoney(otherValue) {
		return new MoneyValue();
	}
	subtract(otherValue) {
		return otherValue.rhsSubtractMoney(this);
	}
	rhsSubtractMoney(lhsMoney) {

	}
	multiply(otherValue) {
		return otherValue.rhsMultiplyMoney(this);
	}
	divide(otherValue) {
		return otherValue.rhsDivideMoney(this);
	}
	greater(otherMoney) {
		return this.euros > otherMoney || this.euros === otherMoney.euros && this.cents > otherMoney.cents;
	}
	greaterEqual(otherMoney) {
		return this.euros > otherMoney || this.euros === otherMoney.euros && this.cents >= otherMoney.cents;
	}
	less(otherMoney) {
		return otherMoney.greaterEqual(this);
	}
	lessEqual(otherMoney) {
		return otherMoney.greater(this);
	}
}

export class UndefinedValue {
	add() {
		return this;
	}
	subtract() {
		return this;
	}

}