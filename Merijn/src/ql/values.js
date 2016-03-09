export class Value {
	dispatch(receiver) {
		throw new Error("Override in subclasses");
	}
	toString() {
		throw new Error("Override in subclasses");
	}
	equals(otherValue) {
		return otherValue.equalsValue(this);
	}
	equalsValue(value) {
		return false;
	}
	equalsBoolean(booleanValue) {
		return this.equalsValue(booleanValue);
	}
	equalsString(stringValue) {
		return this.equalsValue(stringValue);
	}
	equalsNumber(numberValue) {
		return this.equalsValue(numberValue);
	}
	equalsInteger(integerValue) {
		return this.equalsNumber(integerValue);
	}
	equalsFloat(floatValue) {
		return this.equalsNumber(floatValue);
	}
	equalsMoney(moneyValue) {
		return this.equalsValue(moneyValue);
	}
	equalsUndefined(undefinedValue) {
		return this.equalsValue(undefinedValue);
	}
}

export class PrimitiveValue extends Value {
	constructor(value) {
		super();
		this.value = value;
	}
}

export class BooleanValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveBoolean(this, ...args);
	}
	toString() {
		return this.value === true ? "true" : "false";
	}
	equals(otherValue) {
		return otherValue.equalsBoolean(this);
	}
	equalsBoolean(booleanValue) {
		return this.value == booleanValue.value;
	}
	static fromString(str) {
		var value;
		switch (str) {
			case "true":
				value = true;
				break;
			case "false":
				value = false;
				break;
			default:
				throw new Error("Expected string to be either 'true' or 'false', but was '" + str + "'");
		}
		return new BooleanValue(value);
	}
}

export class StringValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveString(this, ...args);
	}
	toString() {
		return this.value;
	}
	equals(otherValue) {
		return otherValue.equalsString(this);
	}
	equalsString(stringValue) {
		return this.value === stringValue.value;
	}
	static fromString(str) {
		return new StringValue(str);
	}
}

export class NumberValue extends PrimitiveValue {}

export class IntegerValue extends NumberValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	equals(otherValue) {
		return otherValue.equalsInteger(this);
	}
	equalsInteger(integerValue) {
		return this.value === integerValue.value;
	}
	static fromString(str) {
		return new IntegerValue(parseInt(str, 10));
	}
}

export class FloatValue extends NumberValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	equals(otherValue) {
		return otherValue.equalsFloat(this);
	}
	equalsFloat(floatValue) {
		return this.value === floatValue.value;
	}
	static fromString(str) {
		return new FloatValue(parseFloat(str));
	}
}

export class MoneyValue extends Value {
	constructor(euros, cents) {
		super();
		this.euros = euros;
		this.cents = cents;
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	equals(otherValue) {
		otherValue.equalsMoney(this);
	}
	equalsMoney(moneyValue) {
		return this.euros === moneyValue.euros && this.cents === moneyValue.cents;
	}
	static fromString(str) {
		throw new Error("todo");
	}
}

export class UndefinedValue extends Value {
	dispatch(receiver, ...args) {
		return receiver.receiveUndefined(this, ...args);
	}
	equals(otherValue) {
		return otherValue.equalsUndefined(this);
	}
	equalsUndefined(undefinedValue) {
		return true;
	}
	toString() {
		return "*undefined*";
	}
}

export class ValueReceiver {
	receiveValue(value, ...args) {}
	receivePrimitiveValue(primitiveValue, ...args) {
		return this.receiveValue(primitiveValue, ...args);
	}
	receiveBoolean(booleanValue, ...args) {
		return this.receivePrimitive(booleanValue, ...args);
	}
	receiveString(stringValue, ...args) {
		return this.receivePrimitive(stringValue, ...args);
	}
	receiveNumber(numberValue, ...args) {
		return this.receivePrimitive(numberValue, ...args);
	}
	receiveInteger(integerValue, ...args) {
		return this.receiveNumber(integerValue, ...args);
	}
	receiveFloat(floatValue, ...args) {
		return this.receiveNumber(floatValue, ...args);
	}
	receiveMoney(moneyValue, ...args) {
		return this.receivePrimitive(moneyValue, ...args);
	}
	receiveUndefined(undefinedValue, ...args) {
		return this.receiveValue(undefinedValue, ...args);
	}
}