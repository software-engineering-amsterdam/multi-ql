package uva.ql.ast.conditionals.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.interfaces.INodeVisitor;

public abstract class Condition extends Node {

	private Expression expression;
	
	public Condition(Node parent, Expression expression, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.expression = expression;
	}
	
	@Override
	abstract public Type getType();

	public void setExpression(Expression expression) {
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
