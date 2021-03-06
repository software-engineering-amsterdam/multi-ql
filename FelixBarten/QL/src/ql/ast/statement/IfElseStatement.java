package ql.ast.statement;

import ql.BaseVisitor;
import ql.ast.Block;
import ql.ast.Expr;
import ql.ast.Statement;

public class IfElseStatement extends Statement {

	private Expr expr; 
	private Block body;
	
	public IfElseStatement(Expr result, Block result2) {
		this.expr = result;
		this.body = result2;

	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public Block getBody() {
		return body;
	}

	public void setBody(Block body) {
		this.body = body;
	}

	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
