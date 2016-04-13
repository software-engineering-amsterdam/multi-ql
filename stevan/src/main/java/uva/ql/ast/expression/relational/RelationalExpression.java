package uva.ql.ast.expression.relational;

import uva.ql.ast.Node;
import uva.ql.ast.expression.BinaryExpression;
import uva.ql.ast.expression.Expression;

public abstract class RelationalExpression extends BinaryExpression{

	public RelationalExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
