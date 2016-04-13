package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.DateType;

public class VariableDate extends Variable {
	
	private final DateType type = new DateType();
	
	public VariableDate(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public DateType getType() {
		return type;
	}
}
