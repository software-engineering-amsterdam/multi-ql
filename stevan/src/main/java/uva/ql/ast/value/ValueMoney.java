package uva.ql.ast.value;

import uva.ql.ast.Node;
import uva.ql.ast.type.MoneyType;

public class ValueMoney extends Value {
	
	private final MoneyType type = new MoneyType();
	
	public ValueMoney(Node parent, String value, int startLine, int startColumn) {
		super(parent, value, startLine, startColumn);
	}

	public MoneyType getType() {
		return type;
	}
}
