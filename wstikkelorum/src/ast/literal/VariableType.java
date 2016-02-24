package ast.literal;

import ast.TreeNode;
import ast.visitor.Types;

public class VariableType extends TreeNode {
	private String type;

	public VariableType(int lineNumber, String type) {
		super(lineNumber);
		this.type = type;
	}

	public Types getType() {
		if (type.equals("boolean")) {
			return Types.BOOLEAN;
		}
		if (type.equals("int")) {
			return Types.INT;
		}
		return Types.STRING;
	}

	@Override
	public String toString() {
		if (type.equals("boolean")) {
			return "boolean";
		}
		if (type.equals("int")) {
			return "int";
		}
		return "string";
	}
}
