package ql2.ast.statement;

import ql2.ast.Block;
import ql2.ast.Statement;

public class IfElseStatement extends Statement {

	private IfStatement ifStatement;
	private Block elseBlock;
	
	public IfElseStatement(IfStatement result, Block result2) {
		// TODO Auto-generated constructor stub
		this.ifStatement = result;
		this.elseBlock = result2;
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

	
	
}
