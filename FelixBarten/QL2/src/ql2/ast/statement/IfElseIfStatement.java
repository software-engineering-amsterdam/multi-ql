package ql2.ast.statement;

import ql2.BaseVisitor;
import ql2.ast.Block;
import ql2.ast.Statement;
import ql2.ast.expression.Expr;

public class IfElseIfStatement extends Statement  {

	private IfStatement ifStatement; 
	private Expr condition;
	private Block elseBlock; 
	
	
	public IfElseIfStatement(IfStatement result, Expr condition, Block result2) {
		this.ifStatement= result;
		this.condition = condition;
		this.elseBlock = result2;
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
