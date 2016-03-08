package eu.bankersen.kevin.ql.ast.type.value;

import java.math.BigDecimal;

public class NumberObject extends QLObject {

    private BigDecimal value;
    private Boolean bool;
    
    protected void setScaling(int scale, int round) {
	value.setScale(scale, round);
    }
    
    @Override
    public QLObject subtract(QLObject o) {
	value = value.subtract((BigDecimal) o.value());
	return this;
    }

    @Override
    public QLObject add(QLObject o) {
	value = value.add((BigDecimal) o.value());
	return this;
    }

    @Override
    public QLObject divide(QLObject o) {
	value = value.divide((BigDecimal) o.value());
	return this;
    }

    @Override
    public QLObject multiply(QLObject o) {
	value = value.multiply((BigDecimal) o.value());
	return this;
    }

    @Override
    public QLObject absolute() {
	value = value.abs();
	return this;
    }

    @Override
    public QLObject negate() {
	value = value.negate();
	return this;
    }

    @Override
    public QLObject or(QLObject o) {
	return null;
    }

    @Override
    public QLObject and(QLObject o) {
	return null;
    }

    @Override
    public QLObject equal(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) == 0;
	return this;
    }

    @Override
    public QLObject greaterOrEqual(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) >= 0;
	return this;
    }

    @Override
    public QLObject greater(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) > 0;
	return this;
    }

    @Override
    public QLObject lowerOrEqual(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) <= 0;
	return this;
    }

    @Override
    public QLObject lower(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) < 0;
	return this;
    }

    @Override
    public QLObject notEqual(QLObject o) {
	bool = value.compareTo(((BigDecimal) o.value())) != 0;
	return this;
    }

    @Override
    public QLObject not() {
	return null;
    }

    @Override
    public Object value() {
	if (bool != null) {
	    return bool;
	} else {
	    return value;
	}
    }

    @Override
    public boolean isSimilar(QLObject o) {
	return o instanceof NumberObject;
    }
    @Override
    public boolean isCompatible(QLObject o) {
	return o instanceof MoneyObject || o instanceof IntegerObject;
    }

    @Override
    public void valueOf(String value) {
	this.value = new BigDecimal(value);
    }

    @Override
    public void valueOf(BigDecimal value) {
	this.value = value;
    }
    
    @Override
    public void valueOf(Integer value) {
	this.value = new BigDecimal(value);
    }

    @Override
    public void valueOf(Boolean value) {
	this.value = value ? new BigDecimal(0) : new BigDecimal(1);
    }
}
