package uva.ql.ast.conditionals;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.INodeVisitor;

public class CondIfStatement extends Condition {

	private Type type = new IfStatement();
	private Expression expression;
	
	public CondIfStatement(Expression expression, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.expression = expression;
	}
	
	public Type getType() {
		return this.type;
	}
	
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
