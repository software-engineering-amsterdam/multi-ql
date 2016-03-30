package eu.bankersen.kevin.ql.ast.values;

import eu.bankersen.kevin.ql.ast.types.QLType;

public abstract class QLValue {

    public abstract Object value();

    public abstract QLType getType();

    public abstract Boolean equals(QLValue value);

    public Boolean equals(UndifinedValue type) {
	return false;
    }

    public Boolean equals(IntegerValue type) {
	return false;
    }

    public Boolean equals(MoneyValue type) {
	return false;
    }

    public Boolean equals(BooleanValue type) {
	return false;
    }

    public Boolean equals(StringValue type) {
	return false;
    }

    public QLValue subtract(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue subtract(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue subtract(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue subtract(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue subtract(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue subtract(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue add(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue add(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue add(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue add(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue add(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue add(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue divide(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue multiply(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue absolute() {
	return new UndifinedValue();
    }

    public QLValue negate() {
	return new UndifinedValue();
    }

    public QLValue or(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue or(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue or(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue or(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue or(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue or(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue and(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue and(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue and(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue and(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue and(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue and(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue equal(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue greaterOrEqual(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue greater(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue lowerOrEqual(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue lower(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(QLValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    public QLValue notEqual(StringValue value) {
	return new UndifinedValue();
    }

    public QLValue not() {
	return new UndifinedValue();
    }

}
