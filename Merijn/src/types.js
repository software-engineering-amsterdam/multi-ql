export class Type {
	accept(visitor) {
		throw new Error("Override in subclasses");
	}
	toString() {
		throw new Error("Override in subclasses");
	}
}

export class BooleanType extends Type  {
	accept(visitor, ...args) {
		visitor.visitBoolean(...args);
	}
	toString() {
		return "boolean";
	}
}

export class StringType extends Type  {
	accept(visitor, ...args) {
		return visitor.visitString(...args);
	}
	toString() {
		return "string";
	}
}

export class IntegerType extends Type {
	accept(visitor, ...args) {
		visitor.visitInteger(...args);
	}
	toString() {
		return "integer";
	}
}

export class FloatType extends Type {
	accept(visitor, ...args) {
		return visitor.visitFloat(...args);
	}
	toString() {
		return "float";
	}
}

export class MoneyType extends Type {
	accept(visitor, ...args) {
		return visitor.visitMoney(...args);
	}
	toString() {
		return "money";
	}
}

export class UndefinedType extends Type {
	accept(visitor, ...args) {
		return visitor.visitUndefined(...args);
	}
	toString() {
		return "undefined";
	}
}

export class TypeVisitor {
	visitBoolean() {}
	visitString() {}
	visitInteger() {}
	visitFloat() {}
	visitMoney() {}
	visitUndefined() {}
}