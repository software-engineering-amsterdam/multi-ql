package uva.ql.ast.expression.logical;

import uva.ql.ast.Node;
import uva.ql.ast.expression.BinaryExpression;
import uva.ql.ast.expression.Expression;

public abstract class LogicalBinaryExpression extends BinaryExpression{

	public LogicalBinaryExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
