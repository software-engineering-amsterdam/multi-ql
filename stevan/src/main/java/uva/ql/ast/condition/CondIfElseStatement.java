package uva.ql.ast.condition;

import uva.ql.ast.Block;
import uva.ql.ast.expression.Expression;
import uva.ql.visitors.INodeVisitor;

public class CondIfElseStatement extends Condition {

	private final Block lhs;
	private final Block rhs;
	
	public CondIfElseStatement(Expression expression, Block lhsBlock, Block rhsBlock, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = lhsBlock;
		this.rhs = rhsBlock;
	}
	
	public Block getLhs() {
		return this.lhs;
	}
	
	public Block getRhs() {
		return this.rhs;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfElseCondition(this);
	}
}
