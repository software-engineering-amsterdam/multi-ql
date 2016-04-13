package uva.ql.ast.expression.relational;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class LessThenExpression extends RelationalExpression {

	public LessThenExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
