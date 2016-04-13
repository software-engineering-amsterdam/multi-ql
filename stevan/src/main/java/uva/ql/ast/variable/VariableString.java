package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.StringType;

public class VariableString extends Variable {
	
	private final StringType type = new StringType();
	
	public VariableString(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public StringType getType() {
		return type;
	}
}
