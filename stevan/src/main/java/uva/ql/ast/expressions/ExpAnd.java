package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.LogicalOperator;
import uva.ql.ast.expressions.types.And;

public class ExpAnd extends LogicalOperator {

	private Type type = new And();
	
	public ExpAnd(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

	public Type getType() {
		return type;
	}
}
