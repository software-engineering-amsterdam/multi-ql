package uva.ql.ast.conditionals.abstracts;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.interfaces.INodeVisitor;

public abstract class Condition extends Node {

	protected Expression expression;
	
	public Condition(Node parent, Expression expression, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.expression = expression;
	}
	
	abstract public EnumType getType();

	abstract public EnumType evalType();

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
