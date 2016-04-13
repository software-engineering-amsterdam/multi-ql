package uva.ql.ast.expression;

import uva.ql.ast.Node;

public abstract class UnaryExpression extends Expression {

	private final Expression lhs;
	
	public UnaryExpression(Node parent, int startLine, int startColumn, Expression lhs) {
		super(parent, startLine, startColumn);
		this.lhs = lhs;
	}

	public Expression getLhs() {
		return lhs;
	}

}
