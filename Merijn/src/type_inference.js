import { BooleanType, StringType, IntegerType, FloatType, MoneyType, UndefinedType, TypeVisitor } from 'src/types';

export class TypeInferer extends TypeVisitor {
	visitBoolean() {
		return new UndefinedType();
	}
	visitString() {
		return new UndefinedType();
	}
	visitInteger() {
		return new UndefinedType();
	}
	visitFloat() {
		return new UndefinedType();
	}
	visitMoney() {
		return new UndefinedType();
	}
	visitUndefined() {
		return new UndefinedType();
	}
}

class AddSubtractTypeInferer extends TypeInferer {
	visitFloat(otherType) {
		return otherType.accept(new FloatAddSubtractTypeInferer());
	}
	visitMoney(otherType) {
		return otherType.accept(new MoneyAddSubtractTypeInferer());
	}
}

class IntegerAddSubtractTypeInferer extends TypeInferer {
	visitInteger() {
		return new IntegerType();
	}
	visitFloat() {
		return new FloatType();
	}
}

class FloatAddSubtractTypeInferer extends TypeInferer {
	visitInteger() {
		return new FloatType();
	}
	visitFloat() {
		return new FloatType();
	}
}

class MoneyAddSubtractTypeInferer extends TypeInferer {
	visitMoney() {
		return new MoneyType();
	}
}

export class AddTypeInferer extends AddSubtractTypeInferer {
	visitString(otherType) {
		return otherType.accept(new StringAddTypeInferer());
	}
	visitInteger(otherType) {
		return otherType.accept(new IntegerAddTypeInferer());
	}
}

class StringAddTypeInferer extends TypeInferer {
	visitBoolean() {
		return new StringType();
	}
	visitString() {
		return new StringType();
	}
	visitInteger() {
		return new StringType();
	}
	visitFloat() {
		return new StringType();
	}
	visitMoney() {
		return new StringType();
	}
}

class IntegerAddTypeInferer extends IntegerAddSubtractTypeInferer  {
	visitString() {
		return new StringType();
	}
}

export class SubtractTypeInferer extends AddSubtractTypeInferer {}

class MultiplyDivideTypeInferer extends TypeInferer {
	visitFloat(otherType) {
		return otherType.accept(new FloatMultiplyDivideTypeInferer());
	}
}

class IntegerMultiplyDivideTypeInferer extends MultiplyDivideTypeInferer {
	visitFloat() {
		return new FloatType();
	}
}

class FloatMultiplyDivideTypeInferer extends MultiplyDivideTypeInferer {
	visitInteger() {
		return new FloatType();
	}
	visitFloat() {
		return new FloatType();
	}
}

export class MultiplyTypeInferer extends MultiplyDivideTypeInferer {
	visitInteger(otherType) {
		return otherType.accept(new IntegerMultiplyTypeInferer());
	}
}

class IntegerMultiplyTypeInferer extends IntegerMultiplyDivideTypeInferer {
	visitInteger() {
		return new IntegerType();
	}
}

export class DivideTypeInferer extends MultiplyDivideTypeInferer {
	visitInteger(otherType) {
		return otherType.accept(new IntegerDivideTypeInferer());
	}
}

class IntegerDivideTypeInferer extends IntegerMultiplyDivideTypeInferer {
	visitInteger() {
		return new FloatType();
	}
}

class OrderingTypeInferer extends TypeInferer {
	visitInteger(otherType) {
		return otherType.accept(new NumberOrderingTypeInferer());
	}
	visitFloat(otherType) {
		return otherType.accpe(new NumberOrderingTypeInferer());
	}
}

class NumberOrderingTypeInferer extends TypeInferer {
	visitInteger() {
		return new BooleanType();
	}
	visitFloat() {
		return new BooleanType();
	}
}

export class GreaterTypeInferer extends OrderingTypeInferer {}
export class GreaterEqualTypeInferer extends OrderingTypeInferer {}
export class LessTypeInferer extends OrderingTypeInferer {}
export class LessEqualTypeInferer extends OrderingTypeInferer {}

class EqualNotEqualTypeInferer extends TypeInferer {
	visitBoolean(otherType) {
		return otherType.accept(new BooleanEqualNotEqualTypeInferer());
	}
	visitString(otherType) {
		return otherType.accept(new StringEqualNotEqualTypeInferer());
	}
	visitInteger(otherType) {
		return otherType.accept(new NumberEqualNotEqualTypeInferer());
	}
	visitFloat(otherType) {
		return otherType.accept(new NumberEqualNotEqualTypeInferer());
	}
	visitMoney(otherType) {
		return otherType.accept(new MoneyEqualNotEqualTypeInferer());
	}
}

class BooleanEqualNotEqualTypeInferer extends TypeInferer {
	visitBoolean() {
		return new BooleanType();
	}
}

class StringEqualNotEqualTypeInferer extends TypeInferer {
	visitString() {
		return new BooleanType();
	}
}

class NumberEqualNotEqualTypeInferer extends TypeInferer {
	visitInteger() {
		return new BooleanType();
	}
	visitFloat() {
		return new BooleanType();
	}
}

class MoneyEqualNotEqualTypeInferer extends TypeInferer {
	visitMoney() {
		return new BooleanType();
	}
}

export class EqualTypeInferer extends EqualNotEqualTypeInferer {}
export class NotEqualTypeInferer extends EqualNotEqualTypeInferer {}

class LogicalTypeInferer extends TypeInferer {
	visitBoolean(otherType) {
		return otherType.accept(new BooleanLogicalTypeInferer());
	}
}

class BooleanLogicalTypeInferer extends TypeInferer {
	visitBoolean() {
		return new BooleanType();
	}
}

export class AndTypeInferer extends LogicalTypeInferer {}
export class OrTypeInferer extends LogicalTypeInferer {}