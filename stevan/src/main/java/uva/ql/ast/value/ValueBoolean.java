package uva.ql.ast.value;

import uva.ql.ast.Node;
import uva.ql.ast.type.BooleanType;

public class ValueBoolean extends Value {
	
	private final BooleanType type = new BooleanType();
	
	public ValueBoolean(Node parent, String value, int startLine, int startColumn) {
		super(parent, value, startLine, startColumn);
	}

	public BooleanType getType() {
		return type;
	}
}
