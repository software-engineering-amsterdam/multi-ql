package ql.ast.types;

public class StringType extends ValueType{
	
	@Override
	public boolean equals(Object obj){
		return obj instanceof StringType;
	}
	
	@Override
	public int hashCode(){
		return 42;
	}
	
	@Override
	public String toString(){
		return "string";
	}
}
