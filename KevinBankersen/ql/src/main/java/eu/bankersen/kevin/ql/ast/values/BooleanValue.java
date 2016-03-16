package eu.bankersen.kevin.ql.ast.values;

import eu.bankersen.kevin.ql.ast.types.BooleanType;
import eu.bankersen.kevin.ql.ast.types.QLType;

public class BooleanValue extends QLValue {

    private final Boolean value;

    public BooleanValue(Boolean value) {
	this.value = value;
    }

    @Override
    public Boolean value() {
	return value;
    }

    @Override
    public QLType getType() {
	return new BooleanType();
    }

    @Override
    public Boolean equals(QLValue value) {
	return value.equals(this);
    }

    @Override
    public Boolean equals(BooleanValue value) {
	return value.value() == this.value ? true : false;
    }

    @Override
    public String toString() {
	return value.toString();
    }

    // Operations
    @Override
    public QLValue subtract(QLValue value) {
	return value.subtract(this);
    }

    @Override
    public QLValue add(QLValue value) {
	return value.add(this);
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
    public QLValue absolute() {
	return new BooleanValue(true);
    }

    @Override
    public QLValue negate() {
	return new BooleanValue(false);
    }

    @Override
    public QLValue or(QLValue value) {
	return value.or(this);
    }

    @Override
    public QLValue or(BooleanValue value) {
	return new BooleanValue(value.value() || this.value);
    }

    @Override
    public QLValue and(QLValue value) {
	return value.and(this);
    }

    @Override
    public QLValue and(BooleanValue value) {
	return new BooleanValue(value.value() && this.value);
    }

    @Override
    public QLValue equal(QLValue value) {
	return value.equal(this);
    }

    @Override
    public QLValue equal(MoneyValue value) {
	return null;
    }

    @Override
    public QLValue equal(BooleanValue value) {
	return new BooleanValue(value.value() == this.value);
    }

    @Override
    public QLValue greaterOrEqual(QLValue value) {
	return value.greaterOrEqual(this);
    }

    @Override
    public QLValue greater(QLValue value) {
	return value.greater(this);
    }

    @Override
    public QLValue lowerOrEqual(QLValue value) {
	return value.lowerOrEqual(this);
    }

    @Override
    public QLValue lower(QLValue value) {
	return value.lower(this);
    }

    @Override
    public QLValue notEqual(QLValue value) {
	return value.notEqual(this);
    }

    @Override
    public QLValue notEqual(BooleanValue value) {
	return new BooleanValue(value.value() != this.value);
    }

    @Override
    public QLValue not() {
	return new BooleanValue(!this.value);
    }
}
