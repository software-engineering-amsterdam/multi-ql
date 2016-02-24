export class Value {
	dispatch(receiver) {
		throw new Error("Override in subclasses");
	}
	toString() {
		throw new Error("Override in subclasses");
	}
}

export class PrimitiveValue extends Value {
	constructor(value) {
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
	static fromString(str) {
		return new StringValue(str);
	}
}

export class IntegerValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	static fromString(str) {
		return new IntegerValue(parseInt(str, 10));
	}
}

export class FloatValue extends PrimitiveValue {
	constructor(value) {
		super(value);
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	static fromString(str) {
		return new FloatValue(parseFloat(str));
	}
}

export class MoneyValue extends Value {
	constructor(euros, cents) {
		this.euros = euros;
		this.cents = cents;
	}
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "" + this.value;
	}
	static fromString(str) {
		throw new Error("todo");
	}
}

export class UndefinedValue extends Value {
	dispatch(receiver, ...args) {
		return receiver.receiveUndefined(this, ...args);
	}
	toString() {
		return "*undefined*";
	}
}

export class ValueReceiver {
	receiveBoolean(booleanValue, ...args) {}
	receiveString(stringValue, ...args) {}
	receiveInteger(integerValue, ...args) {}
	receiveFloat(floatValue, ...args) {}
	receiveMoney(moneyValue, ...args) {}
	receiveUndefined(undefinedValue, ...args) {}
}