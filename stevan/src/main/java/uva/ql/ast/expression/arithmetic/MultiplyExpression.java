package uva.ql.ast.expression.arithmetic;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public class MultiplyExpression extends ArithmeticExpression {

	public MultiplyExpression(Node parent, int startLine, int startColumn,
			Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

}
