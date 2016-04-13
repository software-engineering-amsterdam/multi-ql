package uva.ql.ast.variable;

import uva.ql.ast.Node;
import uva.ql.ast.type.MoneyType;

public class VariableMoney extends Variable {
	
	private final MoneyType type = new MoneyType();
	
	public VariableMoney(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}

	public MoneyType getType() {
		return type;
	}
}
