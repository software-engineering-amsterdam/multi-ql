package eu.bankersen.kevin.ql.ast.values;

public abstract class AbstractValue implements QLValue {

    @Override
    public Boolean equals(UndifinedValue type) {
	return false;
    }

    @Override
    public Boolean equals(IntegerValue type) {
	return false;
    }

    @Override
    public Boolean equals(MoneyValue type) {
	return false;
    }

    @Override
    public Boolean equals(BooleanValue type) {
	return false;
    }

    @Override
    public Boolean equals(StringValue type) {
	return false;
    }

    @Override
    public QLValue subtract(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue subtract(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue subtract(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue subtract(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue subtract(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue subtract(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue add(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue divide(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue multiply(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue absolute() {
	return new UndifinedValue();
    }

    @Override
    public QLValue negate() {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue or(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue and(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue equal(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greaterOrEqual(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue greater(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lowerOrEqual(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue lower(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(QLValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(UndifinedValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(IntegerValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(MoneyValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(BooleanValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue notEqual(StringValue value) {
	return new UndifinedValue();
    }

    @Override
    public QLValue not() {
	return new UndifinedValue();
    }

}
