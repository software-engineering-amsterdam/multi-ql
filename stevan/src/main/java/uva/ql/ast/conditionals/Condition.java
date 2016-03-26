package uva.ql.ast.conditionals;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.INodeVisitor;

public abstract class Condition<T> extends Node {

	protected Expression<T> expression;
	
	public Condition(Node parent, Expression<T> expression, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.expression = expression;
	}
	
	abstract public EnumType getType();

	abstract public EnumType evalType();

	public void setExpression(Expression<T> expression) {
		this.expression = expression;
	}
	
	public Expression<T> getExpression() {
		return this.expression;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitCondition(this);
	}
}
