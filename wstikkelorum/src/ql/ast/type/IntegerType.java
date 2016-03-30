package ql.ast.type;

public class IntegerType extends ValueType{
	@Override
	public boolean equals(Object obj){
		return obj instanceof IntegerType;
	}
	
	@Override
	public int hashCode(){
		return 42;
	}
	
	@Override
	public String toString(){
		return "int";
	}

}
