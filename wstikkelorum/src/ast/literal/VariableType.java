package ast.literal;

import ast.TreeNode;
import ast.visitor.Type;

public class VariableType extends TreeNode {
	private String type;

	public VariableType(int lineNumber, String type) {
		super(lineNumber);
		this.type = type;
	}

	public Type getType() {
		if (type.equals("boolean")) {
			return Type.BOOLEAN;
		}
		if (type.equals("int")) {
			return Type.INT;
		}
		return Type.STRING;
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
