package org.uva.sea.visit;

public class TypeCheckVisitor extends Visitor {
	/* This visitor determines for every node whether it has children having the same type. In the process, the visitor updates the types of all nodes. */
	
	private boolean typeSafe = true;
	
	@Override
	public void visit(Expr expr) {
		int nChildren = expr.children().size();
		if (nChildren >= 2) { 
			// assert lhs != null && rhs != null
			if (! (expr.lhs.type == expr.rhs.type)) {
				typeSafe = false;
				System.out.println("lhs, rhs, this node: " + expr.lhs.type + " " + expr.rhs.type + " " + expr.type);
			}
		} else if (nChildren == 1) {
			if (expr.lhs != null) {
				if (! (expr.lhs.type == expr.type)) {
					typeSafe = false;
				}
			} else {
				if (! (expr.rhs.type == expr.type)) {
					typeSafe = false;
				}
			}
		}
	}
	
	public boolean typesSafe() {
		return this.typeSafe;
	}

}
