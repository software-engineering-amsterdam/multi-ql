package uva.ql.ast.expression.logical;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;
import uva.ql.ast.expression.UnaryExpression;

public abstract class LogicalUnaryExpression extends UnaryExpression{

	public LogicalUnaryExpression(Node parent, int startLine, int startColumn,
			Expression lhs) {
		super(parent, startLine, startColumn, lhs);
	}

}
