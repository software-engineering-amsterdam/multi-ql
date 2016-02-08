package org.uva.sea.visit;

public class Expr implements Visitable {
	Expr lhs, rhs;
	public String name;
	
	public void accept(Visitor visitor) {
		if (lhs != null && rhs != null) {
			lhs.accept(visitor);
			rhs.accept(visitor);
		}
		visitor.visit(this);
	}
}
