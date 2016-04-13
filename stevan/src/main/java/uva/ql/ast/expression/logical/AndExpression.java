package uva.ql.ast.expression.logical;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class AndExpression extends LogicalBinaryExpression {

	public AndExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
