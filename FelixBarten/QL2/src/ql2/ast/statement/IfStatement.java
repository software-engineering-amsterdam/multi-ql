package ql2.ast.statement;

import ql2.BaseVisitor;
import ql2.ast.Block;
import ql2.ast.Statement;
import ql2.ast.expression.Expr;

public class IfStatement extends Statement {

	private Expr condition; 
	private Block block;
	

	public IfStatement(Expr result, Block result2) {
		this.condition = result;
		this.block =		result2;
	}

	public Expr getCondition() {
		return condition;
	}

	public void setCondition(Expr condition) {
		this.condition = condition;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
