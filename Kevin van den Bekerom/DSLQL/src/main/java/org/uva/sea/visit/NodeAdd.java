package org.uva.sea.visit;

public class NodeAdd implements IExprElement {
	public NodeIntLiteral lhs, rhs;
	
	public NodeAdd(NodeIntLiteral lhs, NodeIntLiteral rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public void accept(IExprElementVisitor visitor) {
		lhs.accept(visitor);
		rhs.accept(visitor);
		visitor.visit(this);
	}
}
