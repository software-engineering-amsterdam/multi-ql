package uva.ql.ast.expression.relational;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class GreaterThenExpression extends RelationalExpression {

	public GreaterThenExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
