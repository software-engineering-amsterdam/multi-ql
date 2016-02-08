package org.uva.sea.visit;

public abstract class Expr implements Visitable {
	Expr lhs, rhs;
	public String name;
	public ExprEnum type;
	public Object value = null;
	
	public void accept(Visitor visitor) {
		if (lhs != null && rhs != null) {
			lhs.accept(visitor);
			rhs.accept(visitor);
		}
		visitor.visit(this);
	}
	
	public abstract Object eval();


}
