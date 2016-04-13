package uva.ql.ast.expression.logical;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class NotExpression extends LogicalUnaryExpression {

	public NotExpression(Node parent, int startLine, int startColumn,
			Expression lhs) {
		super(parent, startLine, startColumn, lhs);
	}

}
