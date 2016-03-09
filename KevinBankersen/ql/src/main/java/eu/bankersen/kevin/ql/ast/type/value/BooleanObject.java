package eu.bankersen.kevin.ql.ast.type.value;

import java.math.BigDecimal;

public class BooleanObject extends QLObject {

    private Boolean value;
    
    @Override
    public QLObject subtract(QLObject o) {
	return null;
    }

    @Override
    public QLObject add(QLObject o) {
	return null;
    }

    @Override
    public QLObject divide(QLObject o) {
	return null;
    }

    @Override
    public QLObject multiply(QLObject o) {
	return null;
    }

    @Override
    public QLObject absolute() {
	value = true;
	return this;
    }

    @Override
    public QLObject negate() {
	value = false;
	return this;
    }

    @Override
    public QLObject or(QLObject o) {
	value = value || (Boolean) o.value();
	return this;
    }

    @Override
    public QLObject and(QLObject o) {
	value = value && (Boolean) o.value();
	return this;
    }

    @Override
    public QLObject equal(QLObject o) {
	value = value == (Boolean) o.value();
	return this;
    }

    @Override
    public QLObject greaterOrEqual(QLObject o) {
	return null;
    }

    @Override
    public QLObject greater(QLObject o) {
	return null;
    }

    @Override
    public QLObject lowerOrEqual(QLObject o) {
	return null;
    }

    @Override
    public QLObject lower(QLObject o) {
	return null;
    }

    @Override
    public QLObject notEqual(QLObject o) {
	value = value != (Boolean) o.value();
	return this;
    }
    
    @Override
    public QLObject not() {
	value = !value;
	return this;
    }

    @Override
    public Object value() {
	return value;
    }

    @Override
    public boolean isSimilar(QLObject o) {
	return o instanceof BooleanObject;
    }

    @Override
    public void valueOf(String value) {
	this.value = value.equalsIgnoreCase("true");
    }

    @Override
    public void valueOf(BigDecimal value) {
	this.value = value.compareTo(new BigDecimal(1)) == 1 ? true : false;  
    }

    @Override
    public void valueOf(Integer value) {
	this.value = value == 1 ? true : false;
    }

    @Override
    public void valueOf(Boolean value) {
	this.value = value;
    }
    
    @Override
    public String toString() {
	return value.toString();
    }
}