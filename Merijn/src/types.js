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
		receiver.receiveBoolean(this, ...args);
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

export class IntegerType extends Type {
	dispatch(receiver, ...args) {
		receiver.receiveInteger(this, ...args);
	}
	toString() {
		return "integer";
	}
}

export class FloatType extends Type {
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
	receiveBoolean() {}
	receiveString() {}
	receiveInteger() {}
	receiveFloat() {}
	receiveMoney() {}
	receiveUndefined() {}
}