package org.uva.sea.visit;

public class NodeIntLiteral implements IExprElement {
	public int value;
	
	public NodeIntLiteral(int value) {
		this.value = value;
	}
	
	public void accept(IExprElementVisitor visitor) {
		visitor.visit(this);
	}
}
