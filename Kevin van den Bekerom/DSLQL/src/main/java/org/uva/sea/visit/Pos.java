package org.uva.sea.visit;

public class Pos extends Expr {
	
	public Pos(Expr e) {
		super.lhs = e;
		super.type = ExprEnum.INTLITERAL; //someting weird, remove later!
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Object eval() {
		return 0;
	}
	
}
