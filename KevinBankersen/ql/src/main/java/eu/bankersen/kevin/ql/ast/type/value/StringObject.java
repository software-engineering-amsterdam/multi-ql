package eu.bankersen.kevin.ql.ast.type.value;

import java.math.BigDecimal;

public class StringObject extends QLObject {
    
    private String value;
    private Boolean bool;

    @Override
    public QLObject subtract(QLObject o) {
	return null;
    }

    @Override
    public QLObject add(QLObject o) {
	value += String.valueOf(o.value());
	return this;
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
	return null;
    }

    @Override
    public QLObject negate() {
	return null;
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
	bool = value.equals(String.valueOf(o.value()));
	return this;
    }

    @Override
    public QLObject greaterOrEqual(QLObject o) {
	bool = value.length() >= String.valueOf(o.value()).length();
	return this;
    }

    @Override
    public QLObject greater(QLObject o) {
	bool = value.length() > String.valueOf(o.value()).length();
	return this;
    }

    @Override
    public QLObject lowerOrEqual(QLObject o) {
	bool = value.length() <= String.valueOf(o.value()).length();
	return this;
    }

    @Override
    public QLObject lower(QLObject o) {
	bool = value.length() < String.valueOf(o.value()).length();
	return this;
    }

    @Override
    public QLObject notEqual(QLObject o) {
	bool = !value.equals(String.valueOf(o.value()));
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
	return o instanceof StringObject;
    }

    @Override
    public void valueOf(String value) {
	this.value = value;
    }

    @Override
    public void valueOf(BigDecimal value) {
	this.value = String.valueOf(value);
    }

    @Override
    public void valueOf(Integer value) {
	this.value = String.valueOf(value);
    }

    @Override
    public void valueOf(Boolean value) {
	this.value = String.valueOf(value);
    }
    
    @Override
    public String toString() {
	if (bool != null) {
	    return bool.toString();
	} else {
	    return value;
	}
    }
}