import { BooleanValue, StringValue, IntegerValue, FloatValue, MoneyValue, UndefinedValue, ValueReceiver } from 'src/values';

class Evaluator extends ValueReceiver {
	evaluate(value, ...args) {
		return value.dispatch(this, ...args);
	}
	receiveType() {
		return new UndefinedValue();
	}
}

class PrimitiveEvaluator extends Evaluator {
	constructor(primitiveValue) {
		this.primitiveValue = primitiveValue;
	}
}

class BooleanEvaluator extends Evaluator {
	constructor(booleanValue) {
		this.booleanValue = booleanValue;
	}
}

class StringEvaluator extends Evaluator {
	constructor(stringValue) {
		this.stringValue = stringValue;
	}
}

class NumberEvaluator extends Evaluator {
	constructor(numberValue) {
		this.numberValue = numberValue;
	}
}

class IntegerEvaluator extends Evaluator {
	constructor(integerValue) {
		this.integerValue = integerValue;
	}
}

class FloatEvaluator extends Evaluator {
	constructor(floatValue) {
		this.floatValue = floatValue;
	}
}

class MoneyEvaluator extends Evaluator {
	constructor(moneyValue) {
		this.moneyValue = moneyValue;
	}
}

export class AddEvaluator extends Evaluator {
	receiveBoolean(booleanValue, otherValue) {
		return otherValue.dispatch(new BooleanAddEvaluator(booleanValue));
	}
	receiveString(stringValue, otherValue) {
		return otherValue.dispatch(new StringAddEvaluator(stringValue));
	}
	receiveInteger(integerValue, otherValue) {
		return otherValue.dispatch(new IntegerAddEvaluator(integerValue));
	}
	receiveFloat(floatValue, otherValue) {
		return otherValue.dispatch(new FloatAddEvaluator(floatValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneyAddEvaluator(moneyValue));
	}
}

class BooleanAddEvaluator extends BooleanEvaluator {
	receiveString(stringValue) {
		return new StringValue(this.booleanValue.toString() + stringValue.value);
	}
}

class StringAddEvaluator extends StringEvaluator {
	receiveBoolean(booleanValue) {
		return new StringValue(this.stringValue.value + booleanValue.toString());
	}
	receiveString(stringValue) {
		return new StringValue(this.stringValue.value + stringValue.value);
	}
	receiveNumber(numberValue) {
		return new StringValue(this.stringValue.value + numberValue.value);
	}
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

class IntegerAddEvaluator extends IntegerEvaluator {
	receiveString(stringValue) {
		return new StringValue(this.integerValue.toString() + stringValue.value);
	}
	receiveInteger(integerValue) {
		return new IntegerValue(this.integerValue.value + integerValue.value);
	}
	receiveFloat(floatValue) {
		return new FloatValue(this.integerValue.value + floatValue.value);
	}
}

class FloatAddEvaluator extends FloatEvaluator {
	receiveString(stringValue) {
		return new StringValue(this.floatValue.toString() + stringValue.value);
	}
	receiveNumber(numberValue) {
		return new FloatValue(this.floatValue.value + numberValue.value);
	}
}

class MoneyAddEvaluator extends MoneyEvaluator {
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

export class SubtractEvaluator extends Evaluator {
	receiveInteger(integerValue, otherValue) {
		return otherValue.dispatch(new IntegerSubtractEvaluator(integerValue));
	}
	receiveFloat(floatValue, otherValue) {
		return otherValue.dispatch(new FloatSubtractEvaluator(floatValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneySubtractEvaluator(moneyValue));
	}
}

class IntegerSubtractEvaluator extends IntegerEvaluator {
	receiveInteger(integerValue) {
		return new IntegerValue(this.integerValue.value - integerValue.value);
	}
	receiveFloat(floatValue) {
		return new FloatValue(this.integerValue.value - floatValue.value);
	}
}

class FloatSubtractEvaluator extends FloatEvaluator {
	receiveNumber(numberValue) {
		return new FloatValue(this.floatValue.value - numberValue.value);
	}
}


class MoneySubtractEvaluator extends MoneyEvaluator {
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

export class MultiplyEvaluator extends Evaluator {
	receiveString(stringValue, otherValue) {
		return otherValue.dispatch(new StringMultiplyEvaluator(stringValue));
	}
	receiveInteger(integerValue, otherValue) {
		return otherValue.dispatch(new IntegerMultiplyEvaluator(integerValue));
	}
	receiveFloat(floatValue, otherValue) {
		return otherValue.dispatch(new FloatMultiplyEvaluator(floatValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneyMultiplyEvaluator(moneyValue));
	}
}

class StringMultiplyEvaluator extends StringEvaluator {
	receiveInteger(integerValue) {
		return new StringValue(this.stringValue.value.repeat(integerValue.value));
	}
}

class IntegerMultiplyEvaluator extends IntegerEvaluator {
	receiveInteger(integerValue) {
		return new IntegerValue(this.integerValue.value * integerValue.value);
	}
	receiveFloat(floatValue) {
		return new FloatValue(this.integerValue.value * floatValue.value);
	}
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

class FloatMultiplyEvaluator extends FloatEvaluator {
	receiveNumber(numberValue) {
		return new FloatValue(this.floatValue.value * numberValue.value);
	}
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

class MoneyMultiplyEvaluator extends MoneyEvaluator {
	recieveNumber(numberValue) {
		throw new Error("TODO");
	}
}

export class DivideEvaluator extends Evaluator {
	recieveNumber(numberValue, otherValue) {
		return otherValue.dispatch(new NumberDivideEvaluator(numberValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneyDivideEvaluator(moneyValue));
	}
}

class NumberDivideEvaluator extends NumberEvaluator {
	receiveNumber(numberValue) {
		return new FloatValue(this.numberValue.value / numberValue.value);
	}
}

class MoneyDivideEvaluator extends MoneyEvaluator {
	receiveNumber(numberValue) {
		throw new Error("TODO");
	}
}

export class GreaterEvaluator extends Evaluator {
	receiveNumber(numberValue, otherValue) {
		return otherValue.dispatch(new NumberGreaterEvaluator(numberValue));
	}
}

class NumberGreaterEvaluator extends NumberEvaluator {
	receiveNumber(numberValue) {
		return new BooleanValue(this.numberValue.value > numberValue.value);
	}
}

export class GreaterEqualEvaluator extends Evaluator {
	receiveNumber(numberValue, otherValue) {
		return otherValue.dispatch(new NumberGreaterEqualEvaluator(numberValue));
	}
}

class NumberGreaterEqualEvaluator extends NumberEvaluator {
	receiveNumber(numberValue) {
		return new BooleanValue(this.numberValue.value >= numberValue.value);
	}
}

export class LessEvaluator extends Evaluator {
	receiveNumber(numberValue, otherValue) {
		return otherValue.dispatch(new NumberLessEvaluator(numberValue));
	}
}

class NumberLessEvaluator extends NumberEvaluator {
	receiveNumber(numberValue) {
		return new BooleanValue(this.numberValue.value < numberValue.value);
	}
}

export class LessEqualEvaluator extends Evaluator {
	receiveNumber(numberValue, otherValue) {
		return otherValue.dispatch(new NumberLessEqualEvaluator(numberValue));
	}
}

class NumberLessEqualEvaluator extends NumberEvaluator {
	receiveNumber(numberValue) {
		return new BooleanValue(this.numberValue.value <= numberValue.value);
	}
}

export class EqualEvaluator extends Evaluator {
	receivePrimitive(primitiveValue, otherValue) {
		return otherValue.dispatch(new PrimitiveEqualEvaluator(primitiveValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneyEqualEvaluator(moneyValue));
	}
}

class PrimitiveEqualEvaluator extends BooleanEvaluator {
	receivePrimitive(primitiveValue) {
		return new BooleanValue(this.primitiveValue.value === primitiveValue.value); // strict equality here is enough. Javascript makes no distinction between integers and floats and we allow them to be equal as well
	}
}

class MoneyEqualEvaluator extends MoneyEvaluator {
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

export class NotEqualEvaluator extends Evaluator {
	receivePrimitive(primitiveValue, otherValue) {
		return otherValue.dispatch(new PrimitiveNotEqualEvaluator(primitiveValue));
	}
	receiveMoney(moneyValue, otherValue) {
		return otherValue.dispatch(new MoneyNotEqualEvaluator(moneyValue));
	}
}

class PrimitiveNotEqualEvaluator extends PrimitiveEvaluator {
	receivePrimitive(primitiveValue) {
		return this.primitiveValue.value !== primitiveValue.value;
	}
}

class MoneyNotEqualEvaluator extends MoneyEvaluator {
	receiveMoney(moneyValue) {
		throw new Error("TODO");
	}
}

export class AndEvaluator extends Evaluator {
	receiveBoolean(booleanValue, otherValue) {
		return otherValue.dispatch(new BooleanAndEvaluator(booleanValue));
	}
}

class BooleanAndEvaluator extends BooleanEvaluator {
	receiveBoolean(booleanValue) {
		return new BooleanValue(this.booleanValue.value && booleanValue.value);
	}
}

export class OrEvaluator extends Evaluator {
	receiveBoolean(booleanValue, otherValue) {
		return otherValue.dispatch(new BooleanOrEvaluator(booleanValue));
	}
}

class BooleanOrEvaluator extends BooleanEvaluator {
	receiveBoolean(booleanValue) {
		return new BooleanValue(this.booleanValue.value || booleanValue.value);
	}
}