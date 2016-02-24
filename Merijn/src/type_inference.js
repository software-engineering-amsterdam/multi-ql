import { BooleanType, StringType, IntegerType, FloatType, MoneyType, UndefinedType, TypeReceiver } from 'src/types';

export class TypeInferer extends TypeReceiver {
	receiveBoolean() {
		return new UndefinedType();
	}
	receiveString() {
		return new UndefinedType();
	}
	receiveInteger() {
		return new UndefinedType();
	}
	receiveFloat() {
		return new UndefinedType();
	}
	receiveMoney() {
		return new UndefinedType();
	}
	receiveUndefined() {
		return new UndefinedType();
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
		return new IntegerType();
	}
	receiveFloat() {
		return new FloatType();
	}
}

class FloatAddSubtractTypeInferer extends TypeInferer {
	receiveInteger() {
		return new FloatType();
	}
	receiveFloat() {
		return new FloatType();
	}
}

class MoneyAddSubtractTypeInferer extends TypeInferer {
	receiveMoney() {
		return new MoneyType();
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
		return new StringType();
	}
}

class StringAddTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new StringType();
	}
	receiveString() {
		return new StringType();
	}
	receiveInteger() {
		return new StringType();
	}
	receiveFloat() {
		return new StringType();
	}
	receiveMoney() {
		return new StringType();
	}
}

class IntegerAddTypeInferer extends IntegerAddSubtractTypeInferer  {
	receiveString() {
		return new StringType();
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
		return new FloatType();
	}
}

class FloatMultiplyDivideTypeInferer extends MultiplyDivideTypeInferer {
	receiveInteger() {
		return new FloatType();
	}
	receiveFloat() {
		return new FloatType();
	}
}

export class MultiplyTypeInferer extends MultiplyDivideTypeInferer {
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new IntegerMultiplyTypeInferer());
	}
}

class IntegerMultiplyTypeInferer extends IntegerMultiplyDivideTypeInferer {
	receiveInteger() {
		return new IntegerType();
	}
}

export class DivideTypeInferer extends MultiplyDivideTypeInferer {
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new IntegerDivideTypeInferer());
	}
}

class IntegerDivideTypeInferer extends IntegerMultiplyDivideTypeInferer {
	receiveInteger() {
		return new FloatType();
	}
}

class OrderingTypeInferer extends TypeInferer {
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new NumberOrderingTypeInferer());
	}
	receiveFloat(floatType, otherType) {
		return otherType.dispatch(new NumberOrderingTypeInferer());
	}
}

class NumberOrderingTypeInferer extends TypeInferer {
	receiveInteger() {
		return new BooleanType();
	}
	receiveFloat() {
		return new BooleanType();
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
	receiveInteger(integerType, otherType) {
		return otherType.dispatch(new NumberEqualNotEqualTypeInferer());
	}
	receiveFloat(floatType, otherType) {
		return otherType.dispatch(new NumberEqualNotEqualTypeInferer());
	}
	receiveMoney(moneyType, otherType) {
		return otherType.dispatch(new MoneyEqualNotEqualTypeInferer());
	}
}

class BooleanEqualNotEqualTypeInferer extends TypeInferer {
	receiveBoolean() {
		return new BooleanType();
	}
}

class StringEqualNotEqualTypeInferer extends TypeInferer {
	receiveString() {
		return new BooleanType();
	}
}

class NumberEqualNotEqualTypeInferer extends TypeInferer {
	receiveInteger() {
		return new BooleanType();
	}
	receiveFloat() {
		return new BooleanType();
	}
}

class MoneyEqualNotEqualTypeInferer extends TypeInferer {
	receiveMoney() {
		return new BooleanType();
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
		return new BooleanType();
	}
}

export class AndTypeInferer extends LogicalTypeInferer {}
export class OrTypeInferer extends LogicalTypeInferer {}