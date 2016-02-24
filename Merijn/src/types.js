export class Type {
	dispatch(receiver) {
		throw new Error("Override in subclasses");
	}
	toString() {
		throw new Error("Override in subclasses");
	}
}

export class BooleanType extends Type  {
	dispatch(receiver, ...args) {
		return receiver.receiveBoolean(this, ...args);
	}
	toString() {
		return "boolean";
	}
}

export class StringType extends Type  {
	dispatch(receiver, ...args) {
		return receiver.receiveString(this, ...args);
	}
	toString() {
		return "string";
	}
}

export class NumberType extends Type {
	dispatch(receiver, ...args) {
		return receiver.receiveNumber(this, ...args);
	}
}

export class IntegerType extends NumberType {
	dispatch(receiver, ...args) {
		return receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "integer";
	}
}

export class FloatType extends NumberType {
	dispatch(receiver, ...args) {
		return receiver.receiveFloat(this, ...args);
	}
	toString() {
		return "float";
	}
}

export class MoneyType extends Type {
	dispatch(receiver, ...args) {
		return receiver.receiveMoney(this, ...args);
	}
	toString() {
		return "money";
	}
}

export class UndefinedType extends Type {
	dispatch(receiver, ...args) {
		return receiver.receiveUndefined(this, ...args);
	}
	toString() {
		return "undefined";
	}
}

export class TypeReceiver {
	receiveType(type, ...args) {}
	receiveBoolean(booleanType, ...args) {
		return this.receiveType(booleanType, ...args);
	}
	receiveString(stringType, ...args) {
		return this.receiveType(stringType, ...args);
	}
	receiveNumber(numberType, ...args) {
		return this.receiveType(numberType, ...args);
	}
	receiveInteger(integerType, ...args) {
		return this.receiveNumber(integerType, ...args);
	}
	receiveFloat(floatType, ...args) {
		return this.receiveNumber(floatType, ...args);
	}
	receiveMoney(moneyType, ...args) {
		return this.receiveType(moneyType, ...args);
	}
	receiveUndefined(undefinedType, ...args) {
		return this.receiveType(undefinedType, ...args);
	}
}