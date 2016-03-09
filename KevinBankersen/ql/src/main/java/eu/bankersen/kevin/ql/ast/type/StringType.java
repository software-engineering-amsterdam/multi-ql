package eu.bankersen.kevin.ql.ast.type;

public class StringType extends Type {

    public boolean isSimilar(Type type) {
	return type instanceof StringType;
    }
    
    @Override
    public String toString() {
	return "String";
    }
    
    @Override
    public boolean isString() {
	return true;
    }
    
    @Override
    public String parseValue(String value) {
	return value;
    }
    
    @Override
    public String parseValue(Double value) {
	return value.toString();
    }
    
    @Override
    public String parseValue(Boolean value) {
	return value.toString();
    }
}
