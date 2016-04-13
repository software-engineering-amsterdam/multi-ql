package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.UndefinedType;

public class VariableUndefined extends Variable {
	
	private final UndefinedType type = new UndefinedType();
	
	public VariableUndefined(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public UndefinedType getType() {
		return type;
	}
}
