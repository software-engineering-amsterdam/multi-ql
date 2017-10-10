package ql2.ast.statement;

import ql2.ast.Block;
import ql2.ast.Expr;
import ql2.ast.Statement;

public class IfStatement extends Statement {

	private Expr condition; 
	private Block block;
	

	public IfStatement(Expr result, Block result2) {
		// TODO Auto-generated constructor stub
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
	

}
