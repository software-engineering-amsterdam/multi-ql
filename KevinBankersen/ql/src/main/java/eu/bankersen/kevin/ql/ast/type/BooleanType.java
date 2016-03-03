package eu.bankersen.kevin.ql.ast.type;

import eu.bankersen.kevin.ql.ast.Type;

public class BooleanType extends TypeObject {

    private final Type type = Type.BOOLEAN;

    @Override
    public Type getType() {
	return type;
    }

    @Override
    public boolean compatible(TypeObject typeObject) {
	return sameType(typeObject);
    }
    
    @Override
    public Object parseValue(String value) {
	switch (value.toLowerCase()) {
	case "true" : return true;
	case "false" : return false;
	default : return Type.EMPTY;
	}
    }
    
    @Override
    public Object parseValue(Boolean value) {
	return value;
    }

}
