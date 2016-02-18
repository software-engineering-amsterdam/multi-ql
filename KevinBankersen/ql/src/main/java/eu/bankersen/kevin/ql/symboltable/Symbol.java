package eu.bankersen.kevin.ql.symboltable;

import eu.bankersen.kevin.ql.ast.Type;

public class Symbol {

    private final Type type;
    private Object value;
    private Boolean active;


    Symbol(final Type type) {
	this.type = type;
	this.active = true;
    }

    public final void setActive(final Boolean active) {
	this.active = active;
    }

    public final Boolean getActive() {
	return active;
    }


    public final Type getType() {
	return type;
    }

    public final Object getValue() {
	return value;
    }

    public final void setValue(final Object value) {
	this.value = value;
    }

    @Override
    public final String toString() {
	return type.toString() + "=" + value.toString();

    }

}
