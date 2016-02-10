package org.uva.sea.ql.ast;

public class ASTNode implements Visitable {
	ASTID nodeID;
	
	public ASTNode(ASTID nodeID) {
		this.nodeID = nodeID;
	}

	public void accept(Visitor visitor) {
		
	}
	
}
