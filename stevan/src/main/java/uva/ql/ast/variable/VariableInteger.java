package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.IntegerType;

public class VariableInteger extends Variable {
	
	private final IntegerType type = new IntegerType();
	
	public VariableInteger(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public IntegerType getType() {
		return type;
	}
}
