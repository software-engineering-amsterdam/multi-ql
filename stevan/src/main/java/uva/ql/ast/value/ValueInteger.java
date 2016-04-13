package uva.ql.ast.value;

import uva.ql.ast.Node;
import uva.ql.ast.type.IntegerType;

public class ValueInteger extends Value {
	
	private final IntegerType type = new IntegerType();
	
	public ValueInteger(Node parent, String value, int startLine, int startColumn) {
		super(parent, value, startLine, startColumn);
	}

	public IntegerType getType() {
		return type;
	}
}
