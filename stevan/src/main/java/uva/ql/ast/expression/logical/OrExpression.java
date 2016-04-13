package uva.ql.ast.expression.logical;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class OrExpression extends LogicalBinaryExpression {

	public OrExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
