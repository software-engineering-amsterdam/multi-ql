package ql.ast.types;

public class BooleanType extends ValueType{
	@Override
	public boolean equals(Object obj){
		return obj instanceof BooleanType;
	}
	
	@Override
	public int hashCode(){
		return 42;
	}
	
	@Override
	public String toString(){
		return "boolean";
	}

}
