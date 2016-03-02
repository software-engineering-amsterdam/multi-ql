import * as types from 'src/types';

export class TypeInferer extends types.TypeReceiver {
	receiveType() {
		return new types.UndefinedType();
	}
}

export class NegationTypeInferer extends TypeInferer {
	receiveInteger() {
		return new types.IntegerType();
	}
	receiveFloat() {
		return new types.FloatType();
	}
}

export class NotTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new types.BooleanType();
	}
}

class AddSubtractTypeInferer extends TypeInferer {
	receiveFloat(floatType, otherType) {
		return otherType.dispatch(new FloatAddSubtractTypeInferer());
	}
	receiveMoney(moneyType, otherType) {
		return otherType.dispatch(new MoneyAddSubtractTypeInferer());
	}
}

class IntegerAddSubtractTypeInferer extends TypeInferer {
	receiveInteger() {
		return new types.IntegerType();
	}
	receiveFloat() {
		return new types.FloatType();
	}
}

class FloatAddSubtractTypeInferer extends TypeInferer {
	receiveNumber() {
		return new types.FloatType();
	}
}

class MoneyAddSubtractTypeInferer extends TypeInferer {
	receiveMoney() {
		return new types.MoneyType();
	}
}

export class AddTypeInferer extends AddSubtractTypeInferer {
	receiveBoolean(booleanType, otherType) {
		return otherType.dispatch(new BooleanAddTypeInferer());
	}
	receiveString(stringType, otherType) {
		return otherType.dispatch(new StringAddTypeInferer());
	}
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new IntegerAddTypeInferer());
	}
}

class BooleanAddTypeInferer extends TypeInferer {
	receiveString() {
		return new types.StringType();
	}
}

class StringAddTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new types.StringType();
	}
	receiveString() {
		return new types.StringType();
	}
	receiveNumber() {
		return new types.StringType();
	}
	receiveMoney() {
		return new types.StringType();
	}
}

class IntegerAddTypeInferer extends IntegerAddSubtractTypeInferer  {
	receiveString() {
		return new types.StringType();
	}
}

export class SubtractTypeInferer extends AddSubtractTypeInferer {}

class MultiplyDivideTypeInferer extends TypeInferer {
	receiveFloat(floatType, otherType) {
		return otherType.dispatch(new FloatMultiplyDivideTypeInferer());
	}
}

class IntegerMultiplyDivideTypeInferer extends MultiplyDivideTypeInferer {
	receiveFloat() {
		return new types.FloatType();
	}
}

class FloatMultiplyDivideTypeInferer extends MultiplyDivideTypeInferer {
	receiveNumber() {
		return new types.FloatType();
	}
}

export class MultiplyTypeInferer extends MultiplyDivideTypeInferer {
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new IntegerMultiplyTypeInferer());
	}
}

class IntegerMultiplyTypeInferer extends IntegerMultiplyDivideTypeInferer {
	receiveInteger() {
		return new types.IntegerType();
	}
}

export class DivideTypeInferer extends MultiplyDivideTypeInferer {
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new IntegerDivideTypeInferer());
	}
}

class IntegerDivideTypeInferer extends IntegerMultiplyDivideTypeInferer {
	receiveInteger() {
		return new types.FloatType();
	}
}

class OrderingTypeInferer extends TypeInferer {
	receiveNumber(numberType, otherType) {
		return otherType.dispatch(new NumberOrderingTypeInferer());
	}
}

class NumberOrderingTypeInferer extends TypeInferer {
	receiveNumber() {
		return new types.BooleanType();
	}
}

export class GreaterTypeInferer extends OrderingTypeInferer {}
export class GreaterEqualTypeInferer extends OrderingTypeInferer {}
export class LessTypeInferer extends OrderingTypeInferer {}
export class LessEqualTypeInferer extends OrderingTypeInferer {}

class EqualNotEqualTypeInferer extends TypeInferer {
	receiveBoolean(booleanType, otherType) {
		return otherType.dispatch(new BooleanEqualNotEqualTypeInferer());
	}
	receiveString(stringType, otherType) {
		return otherType.dispatch(new StringEqualNotEqualTypeInferer());
	}
	receiveNumber(numberType, otherType) {
		return otherType.dispatch(new NumberEqualNotEqualTypeInferer());
	}
	receiveMoney(moneyType, otherType) {
		return otherType.dispatch(new MoneyEqualNotEqualTypeInferer());
	}
}

class BooleanEqualNotEqualTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new types.BooleanType();
	}
}

class StringEqualNotEqualTypeInferer extends TypeInferer {
	receiveString() {
		return new types.BooleanType();
	}
}

class NumberEqualNotEqualTypeInferer extends TypeInferer {
	receiveNumber() {
		return new types.BooleanType();
	}
}

class MoneyEqualNotEqualTypeInferer extends TypeInferer {
	receiveMoney() {
		return new types.BooleanType();
	}
}

export class EqualTypeInferer extends EqualNotEqualTypeInferer {}
export class NotEqualTypeInferer extends EqualNotEqualTypeInferer {}

class LogicalTypeInferer extends TypeInferer {
	receiveBoolean(booleanType, otherType) {
		return otherType.dispatch(new BooleanLogicalTypeInferer());
	}
}

class BooleanLogicalTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new types.BooleanType();
	}
}

export class AndTypeInferer extends LogicalTypeInferer {}
export class OrTypeInferer extends LogicalTypeInferer {}