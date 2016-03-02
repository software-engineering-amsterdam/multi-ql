package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.ArithmeticOperator;
import uva.ql.ast.expressions.types.Multiply;

public class ExpMultiply extends ArithmeticOperator {

	private Type type = new Multiply();
	
	public ExpMultiply(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
	}

	public Type getType() {
		return this.type;
	}
}
