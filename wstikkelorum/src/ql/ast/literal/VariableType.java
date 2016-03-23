package ql.ast.literal;

import ql.ast.TreeNode;
import ql.ast.types.BooleanType;
import ql.ast.types.IntegerType;
import ql.ast.types.StringType;
import ql.ast.types.ValueType;

public class VariableType extends TreeNode {
	private ValueType type;

	public VariableType(int lineNumber, String type) {
		super(lineNumber);
		createTypeFromString(type);
	}
	
	private void createTypeFromString(String type){
		if(type.equals("boolean")){
			this.type = new BooleanType();
			return;
		}
		if(type.equals("int")){
			this.type = new IntegerType();
			return;
		}
		//else type equals "string"
		this.type = new StringType();
	}

	public ValueType getType() {
		return type;
	}
}
