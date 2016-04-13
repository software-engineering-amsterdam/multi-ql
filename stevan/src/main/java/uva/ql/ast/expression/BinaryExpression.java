package uva.ql.ast.expression;

import uva.ql.ast.Node;

public abstract class BinaryExpression extends UnaryExpression{

	private final Expression rhs;
	
	public BinaryExpression(Node parent, int startLine, int startColumn, Expression lhs, Expression rhs) {
		super(parent, startLine, startColumn, lhs);
		this.rhs = rhs;
	}

	public Expression getRhs() {
		return rhs;
	}

}
