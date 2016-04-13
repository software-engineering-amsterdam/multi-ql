package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.BooleanType;

public class VariableBoolean extends Variable {
	
	private final BooleanType type = new BooleanType();
	
	public VariableBoolean(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public BooleanType getType() {
		return type;
	}
}
