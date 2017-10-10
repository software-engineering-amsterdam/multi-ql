package ql2.ast.statement;

import ql2.ast.Block;
import ql2.ast.Expr;
import ql2.ast.Statement;

public class IfElseIfStatement extends Statement  {

	private IfStatement ifStat; 
	private Expr condition;
	private Block elseBlock; 
	
	
	public IfElseIfStatement(IfStatement result, Expr condition, Block result2) {
		// TODO Auto-generated constructor stub
		this.ifStat= result;
		this.condition = condition;
		this.elseBlock = result2;
	}

}
