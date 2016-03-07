package eu.bankersen.kevin.ql.typechecker.symboltable;

import eu.bankersen.kevin.ql.ast.type.Type;

public class Symbol {

    private final Boolean computed;
    private final String name;
    private final String question;
    private final Type type;
    private Object value;
    private Boolean active;


    public Symbol(Boolean computed, String name, String question, Type type, Object value) {
	this.computed = computed;
	this.name = name;
	this.question = question;
	this.type = type;
	this.active = true;
	this.value = value;
    }

    public String getName() {
	return name;
    }
    
    public Boolean isComputed() {
	return computed;
    }

    public String getQuestion() {
	return question;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public Boolean getActive() {
	return active;
    }


    public Type getType() {
	return type;
    }

    public Object getValue() {
	return value;
    }

    public void setValue(Object value) {
	this.value = value;
    }

    @Override
    public String toString() {
	if (active & value != null) {
	    return name + "=" + value.toString() + "\n";
	} else {
	    return "";
	}
    }

}
