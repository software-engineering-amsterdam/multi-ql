package eu.bankersen.kevin.ql.ast.type;

import eu.bankersen.kevin.ql.ast.Type;

public class IntType extends TypeObject {

    private final Type type = Type.INTEGER;

    @Override
    public Type getType() {
	return type;
    }

    @Override
    public boolean compatible(TypeObject typeObject) {
	switch(typeObject.getType()) {
	case MONEY: return true;
	case INTEGER: return true;
	default : return false;
	}
    }
    
    @Override 
    public Object parseValue(Integer value) {
	return value;
    }

    @Override 
    public Object parseValue(String value) {
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException e) {
	    return Type.EMPTY;
	}
    }
}
