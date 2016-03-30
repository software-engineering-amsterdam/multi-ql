package eu.bankersen.kevin.ql.ast.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

import eu.bankersen.kevin.ql.ast.types.MoneyType;
import eu.bankersen.kevin.ql.ast.types.QLType;

public class MoneyValue extends QLValue {

    private final BigDecimal value;

    public MoneyValue(Integer value) {
	this.value = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public MoneyValue(BigDecimal value) {
	this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
	return "€" + value.toString();
    }

    @Override
    public BigDecimal value() {
	return value;
    }

    @Override
    public QLType getType() {
	return new MoneyType();
    }

    @Override
    public Boolean equals(QLValue value) {
	return value.equals(this);
    }

    @Override
    public Boolean equals(MoneyValue value) {
	return value.value().compareTo(this.value) == 0 ? true : false;
    }

    // Operations
    @Override
    public QLValue subtract(QLValue value) {
	return value.subtract(this);
    }

    @Override
    public QLValue subtract(MoneyValue value) {
	return new MoneyValue(value.value().subtract(this.value));
    }

    @Override
    public QLValue add(QLValue value) {
	return value.add(this);
    }

    @Override
    public QLValue add(MoneyValue value) {
	return new MoneyValue(value.value().add(this.value));
    }

    @Override
    public QLValue add(StringValue value) {
	return new StringValue(value.value().concat(this.toString()));
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
	return new MoneyValue(this.value.abs());
    }

    @Override
    public QLValue negate() {
	return new MoneyValue(this.value.negate());
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
    public QLValue equal(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) == 0 ? true : false);
    }

    @Override
    public QLValue greaterOrEqual(QLValue value) {
	return value.greaterOrEqual(this);
    }

    @Override
    public QLValue greaterOrEqual(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) >= 0 ? true : false);
    }

    @Override
    public QLValue greater(QLValue value) {
	return value.greater(this);
    }

    @Override
    public QLValue greater(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) > 0 ? true : false);
    }

    @Override
    public QLValue lowerOrEqual(QLValue value) {
	return value.lowerOrEqual(this);
    }

    @Override
    public QLValue lowerOrEqual(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) <= 0 ? true : false);
    }

    @Override
    public QLValue lower(QLValue value) {
	return value.lower(this);
    }

    @Override
    public QLValue lower(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) < 0 ? true : false);
    }

    @Override
    public QLValue notEqual(QLValue value) {
	return value.notEqual(this);
    }

    @Override
    public QLValue notEqual(MoneyValue value) {
	return new BooleanValue(value.value().compareTo(this.value) != 0 ? true : false);
    }
}
