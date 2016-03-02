package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.ArithmeticOperator;
import uva.ql.ast.expressions.types.Add;

public class ExpAdd extends ArithmeticOperator {

	private Add type = new Add();
	
	public ExpAdd(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

	public Add getType() {
		return this.type;
	}
}
