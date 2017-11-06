package ql2.ast.statement;

import ql2.BaseVisitor;
import ql2.ast.Block;
import ql2.ast.Statement;

public class IfElseStatement extends Statement {

	private IfStatement ifStatement;
	private Block elseBlock;
	
	public IfElseStatement(IfStatement ifStat, Block block) {
		this.ifStatement = ifStat;
		this.elseBlock = block;
	}

	public IfStatement getIfStatement() {
		return ifStatement;
	}

	public void setIfStatement(IfStatement ifStatement) {
		this.ifStatement = ifStatement;
	}

	public Block getElseBlock() {
		return elseBlock;
	}

	public void setElseBlock(Block elseBlock) {
		this.elseBlock = elseBlock;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	
}
