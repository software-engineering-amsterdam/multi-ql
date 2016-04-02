package ql.ast.type;

import ql.ast.value.BooleanValue;

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

	@Override
	public BooleanValue getDefaultValue() {
		return new BooleanValue(false);
	}

}
