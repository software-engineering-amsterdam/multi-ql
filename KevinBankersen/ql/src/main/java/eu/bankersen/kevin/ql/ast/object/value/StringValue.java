package eu.bankersen.kevin.ql.ast.object.value;

import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.type.StringType;

public class StringValue extends AbstractValue {

    private final String value;

    public StringValue(String value) {
	this.value = value;
    }

    @Override
    public String value() {
	return value;
    }

    @Override
    public QLType getType() {
	return new StringType();
    }

    @Override
    public String toString() {
	return value;
    }

    @Override
    public Boolean equals(QLValue value) {
	return value.equals(this);
    }

    @Override
    public Boolean equals(StringValue value) {
	return value.value().equals(this.value);
    }

    @Override
    public QLValue subtract(QLValue value) {
	return value.subtract(this);
    }

    @Override
    public QLValue add(QLValue value) {
	return value.add(this);
    }

    @Override
    public QLValue add(StringValue value) {
	return new StringValue(value.value + this.value.toString());
    }

    @Override
    public QLValue divide(QLValue value) {
	return value.divide(this);
    }

    @Override
    public QLValue multiply(QLValue value) {
	return value.multiply(this);
    }

    @Override
    public QLValue or(QLValue value) {
	return value.or(this);
    }

    @Override
    public QLValue and(QLValue value) {
	return value.and(this);
    }

    @Override
    public QLValue and(StringValue value) {
	return new BooleanValue(value.value().equals(this.value));
    }

    @Override
    public QLValue equal(QLValue value) {
	return value.equal(this);
    }

    @Override
    public QLValue equal(StringValue value) {
	return new BooleanValue(value.value().length() == this.value.length());
    }

    @Override
    public QLValue greaterOrEqual(QLValue value) {
	return value.greaterOrEqual(this);
    }

    @Override
    public QLValue greaterOrEqual(StringValue value) {
	return new BooleanValue(value.value().length() >= this.value.length());
    }

    @Override
    public QLValue greater(QLValue value) {
	return value.greater(this);
    }

    @Override
    public QLValue greater(StringValue value) {
	return new BooleanValue(value.value().length() > this.value.length());
    }

    @Override
    public QLValue lowerOrEqual(QLValue value) {
	return value.lowerOrEqual(this);
    }

    @Override
    public QLValue lowerOrEqual(StringValue value) {
	return new BooleanValue(value.value().length() <= this.value.length());
    }

    @Override
    public QLValue lower(QLValue value) {
	return value.lower(this);
    }

    @Override
    public QLValue lower(StringValue value) {
	return new BooleanValue(value.value().length() < this.value.length());
    }

    @Override
    public QLValue notEqual(QLValue value) {
	return value.notEqual(this);
    }

    @Override
    public QLValue notEqual(StringValue value) {
	return new BooleanValue(!value.value().equals(this.value));
    }
}
