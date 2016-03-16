package eu.bankersen.kevin.ql.ast.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

import eu.bankersen.kevin.ql.ast.types.IntegerType;
import eu.bankersen.kevin.ql.ast.types.QLType;

public class IntegerValue extends QLValue {

    private final BigDecimal value;

    public IntegerValue(Integer value) {
	this.value = new BigDecimal(value).setScale(0, RoundingMode.HALF_UP);
    }

    public IntegerValue(BigDecimal value) {
	this.value = value.setScale(0, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal value() {
	return value;
    }

    @Override
    public QLType getType() {
	return new IntegerType();
    }

    @Override
    public Boolean equals(QLValue value) {
	return value.equals(this);
    }

    @Override
    public Boolean equals(IntegerValue value) {
	return value.value().compareTo(this.value) == 0 ? true : false;
    }

    @Override
    public String toString() {
	return value.toString();
    }

    @Override
    public QLValue subtract(QLValue value) {
	return value.subtract(this);
    }

    @Override
    public QLValue subtract(IntegerValue value) {
	return new IntegerValue(value.value().subtract(this.value));
    }

    @Override
    public QLValue add(QLValue value) {
	return value.add(this);
    }

    @Override
    public QLValue add(IntegerValue value) {
	return new IntegerValue(value.value().add(this.value));
    }

    @Override
    public QLValue add(StringValue value) {
	return new StringValue(value.value().concat(this.value.toString()));
    }

    @Override
    public QLValue divide(QLValue value) {
	return value.divide(this);
    }

    @Override
    public QLValue divide(IntegerValue value) {
	return new IntegerValue(value.value().divide(this.value));
    }

    @Override
    public QLValue divide(MoneyValue value) {
	return new MoneyValue(value.value().divide(this.value));
    }

    @Override
    public QLValue multiply(QLValue value) {
	return value.multiply(this);
    }

    @Override
    public QLValue multiply(IntegerValue value) {
	return new IntegerValue(value.value().multiply(this.value));
    }

    @Override
    public QLValue multiply(MoneyValue value) {
	return new MoneyValue(value.value().multiply(this.value));
    }

    @Override
    public QLValue absolute() {
	return new IntegerValue(this.value.abs());
    }

    @Override
    public QLValue negate() {
	return new IntegerValue(this.value.negate());
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
    public QLValue equal(QLValue value) {
	return value.equal(this);
    }

    @Override
    public QLValue equal(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) == 0 ? true : false);
    }

    @Override
    public QLValue greaterOrEqual(QLValue value) {
	return value.greaterOrEqual(this);
    }

    @Override
    public QLValue greaterOrEqual(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) >= 0 ? true : false);
    }

    @Override
    public QLValue greater(QLValue value) {
	return value.greater(this);
    }

    @Override
    public QLValue greater(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) > 0 ? true : false);
    }

    @Override
    public QLValue lowerOrEqual(QLValue value) {
	return value.lowerOrEqual(this);
    }

    @Override
    public QLValue lowerOrEqual(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) <= 0 ? true : false);
    }

    @Override
    public QLValue lower(QLValue value) {
	return value.lower(this);
    }

    @Override
    public QLValue lower(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) < 0 ? true : false);
    }

    @Override
    public QLValue notEqual(QLValue value) {
	return value.notEqual(this);
    }

    @Override
    public QLValue notEqual(IntegerValue value) {
	return new BooleanValue(value.value().compareTo(this.value) != 0 ? true : false);
    }
}
