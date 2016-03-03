package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.ArithmeticOperator;
import uva.ql.ast.expressions.types.Minus;

public class ExpMinus extends ArithmeticOperator {

	private Minus type = new Minus();
	
	public ExpMinus(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}
	
	public Minus getType() {
		return this.type;
	}
}
