package eu.bankersen.kevin.ql.ast.type;

import eu.bankersen.kevin.ql.ast.Type;

public class StringType extends TypeObject {

    private final Type type = Type.STRING;

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
	return value;
    }
    
    @Override
    public Object parseValue(Integer value) {
	return value.toString();
    }
    
    @Override
    public Object parseValue(Boolean value) {
	return value.toString();
    }
}
