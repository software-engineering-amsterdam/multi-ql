package ql.ast.type;

import ql.ast.value.StringValue;

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

	@Override
	public StringValue getDefaultValue() {
		return new StringValue("");
	}
}
