package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.ASTNode;

public class Type extends ASTNode {

	String type;

	public Type(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
