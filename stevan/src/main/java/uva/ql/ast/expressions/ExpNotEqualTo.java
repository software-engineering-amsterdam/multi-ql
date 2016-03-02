package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.RelationalOperator;
import uva.ql.ast.expressions.types.NotEqualTo;

public class ExpNotEqualTo extends RelationalOperator {

	private Type type = new NotEqualTo();
	
	public ExpNotEqualTo(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

	public Type getType() {
		return this.type;
	}
}
