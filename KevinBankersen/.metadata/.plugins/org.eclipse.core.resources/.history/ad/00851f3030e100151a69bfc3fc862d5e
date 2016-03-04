package eu.bankersen.kevin.ql.ast.type;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class TypeObject {
    
    public abstract Type getType();
    
    public abstract boolean compatible(TypeObject typeObject);
    
    public boolean sameType(TypeObject typeObject) {
	return typeObject.getType().equals(getType());
    }
    
    public Object parseValue(String value) {
	return Type.EMPTY;
    }
    
    public Object parseValue(Integer value) {
	return Type.EMPTY;
    }
    
    public Object parseValue(Boolean value) {
	return Type.EMPTY;
    }

}
