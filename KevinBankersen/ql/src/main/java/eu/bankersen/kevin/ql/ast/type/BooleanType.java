package eu.bankersen.kevin.ql.ast.type;

public class BooleanType extends Type {

    @Override
    public boolean isSimilar(Type type) {
	return type instanceof BooleanType;
    }
    
    @Override
    public boolean isBoolean() {
	return true;
    }
    
    @Override
    public String toString() {
	return "Boolean";
    }
    
    @Override
    public Boolean parseValue(String value) {
	return value.equalsIgnoreCase("true");
    }
    
    @Override
    public Boolean parseValue(Boolean value) {
	return value;
    }
    

}
