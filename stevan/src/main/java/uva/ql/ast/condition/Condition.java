package uva.ql.ast.condition;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;
import uva.ql.visitors.INodeVisitor;

public abstract class Condition extends Node {

	protected final Expression expression;
	
	public Condition(Node parent, Expression expression, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitCondition(this);
	}
}
