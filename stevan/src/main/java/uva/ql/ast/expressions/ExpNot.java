package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.SingleLogicalOperator;
import uva.ql.ast.expressions.types.Not;

public class ExpNot extends SingleLogicalOperator {

	private Type type = new Not();
	
	public ExpNot(Node parent, Node lhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs);
	}

	public Type getType() {
		return type;
	}
}
