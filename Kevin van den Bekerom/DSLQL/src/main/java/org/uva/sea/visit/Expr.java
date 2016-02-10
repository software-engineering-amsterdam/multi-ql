package org.uva.sea.visit;

import java.util.ArrayList;
import java.util.List;

public abstract class Expr implements Visitable {
	Expr lhs, rhs;
	public String name;
	public ExprEnum type;
	public Object value = null;
	
	public void accept(Visitor visitor) {
		if (lhs != null) {
			lhs.accept(visitor); 
		}
		if (rhs != null) {
			rhs.accept(visitor);
		}
		visitor.visit(this);
		
	}
	
	public List<Expr> children() {
		List<Expr> children = new ArrayList<Expr>();
		if (lhs != null) {
			children.add(lhs);
		}
		if (rhs != null) {
			children.add(rhs);
		}
		return children;
	}
	
	public abstract Object eval();


}
