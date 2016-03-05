package uva.ql.ast.conditionals;

import uva.ql.ast.Block;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.INodeVisitor;

public class CondIfElseStatement extends Condition {

	private Type type = new IfStatement();
	private Block lhs;
	private Block rhs;
	
	public CondIfElseStatement(Expression expression, Block lhsBlock, Block rhsBlock, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = lhsBlock;
		this.rhs = rhsBlock;
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	public void setLhs(Block lhs) {
		this.lhs = lhs;
	}
	
	public Block getLhs() {
		return this.lhs;
	}
	
	public void setRhs(Block rhs) {
		this.rhs = rhs;
	}
	
	public Block getRhs() {
		return this.rhs;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfElseCondition(this);
	}
}
