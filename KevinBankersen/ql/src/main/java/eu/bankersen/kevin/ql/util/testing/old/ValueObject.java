package eu.bankersen.kevin.ql.util.testing.old;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class ValueObject {

    private final Type type;
    private Object value;

    ValueObject(Type type, Object value) {
	this.type = type;
	this.value = value;
    }

    public Type getType() {
	return type;
    }

    public Object getValue() {
	return value;
    }

    public boolean sameType(ValueObject type) {
	return this.type.equals(type.getType());
    }
  
    
    public boolean compatibleType(ValueObject type) {
	Type inputType = type.getType();
	
	switch(inputType) {
	case MONEY : return numberType();
	case INTEGER : return numberType();
	case STRING : return true;
	case BOOLEAN : return true;
	default : return false;
	}
    }
    
    private boolean numberType() {
	switch(type) {
	case MONEY : return true;
	case INTEGER : return true;
	default : return false;
	}
    }
 
    // Setting the Value
    public void setValue(String value) {
	switch(type) {
	case STRING : this.value = value;
	break;

	case INTEGER : setIntValue(value);
	break;

	case BOOLEAN : setBoolValue(value);
	break;

	default : break;
	}
    }

    public void setValue(Integer value) {
	switch(type) {
	case STRING : setStrValue(value);
	break;

	case INTEGER : this.value = value;
	break;

	case BOOLEAN : setBoolValue(value);
	break;

	default : break;
	}
    }

    public void setValue(Boolean value) {
	switch(type) {
	case STRING : setStrValue(value);
	break;

	case INTEGER : setIntValue(value);
	break;

	case BOOLEAN : this.value = value;
	break;

	default : break;
	}
    }

    // Setting Integer Values
    private void setIntValue(String value) {

	try {
	    this.value = Integer.valueOf(value);
	} catch (NumberFormatException e) {
	    this.value = Type.EMPTY;
	}
    }

    private void setIntValue(Boolean value) {
    }

    // Setting Boolean Values
    private void setBoolValue(String value) {
	switch (value.toLowerCase()) {
	case "true" : this.value = true;
	break;

	case "false" : this.value = false;
	break;

	default : break;
	}
    }

    private void setBoolValue(Integer value) {
    }

    // Setting String Values
    private void setStrValue(Boolean value) {
	this.value = String.valueOf(value);
    }

    private void setStrValue(Integer value) {
	this.value = String.valueOf(value);
    }

}
