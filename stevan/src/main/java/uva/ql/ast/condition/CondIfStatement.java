package uva.ql.ast.condition;

import uva.ql.ast.Block;
import uva.ql.ast.expression.Expression;
import uva.ql.visitors.INodeVisitor;

public class CondIfStatement extends Condition {

	private final Block lhs;
	
	public CondIfStatement(Expression expression, Block block, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = block;
	}
	
	public Block getLhs() {
		return this.lhs;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfCondition(this);
	}
}
