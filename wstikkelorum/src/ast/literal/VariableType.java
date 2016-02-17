package ast.literal;

import ast.visitor.Types;

public class VariableType {
	private String type;

	public VariableType(String type) {
		this.type = type;
	}
	
	public Types getType(){
		if(type.equals("boolean")){
			return Types.BOOLEAN;
		}
		if(type.equals("int")){
			return Types.INT;
		}
		return Types.STRING;
	}
	
	@Override
	public String toString(){
		if(type.equals("boolean")){
			return "boolean";
		}
		if(type.equals("int")){
			return "int";
		}
		return "string";
	}
}
