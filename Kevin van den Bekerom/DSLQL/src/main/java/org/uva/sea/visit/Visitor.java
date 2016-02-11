package org.uva.sea.visit;

public interface Visitor {
	public abstract void visit(Expr expr);
	public void visit(Add add);
	public void visit(IntLiteral il);
	public void visit(Pos pos);
	
}
