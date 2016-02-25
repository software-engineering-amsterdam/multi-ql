package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;

public class Type extends ASTNode {
	
	String type;
	
	public Type() {
		this.type = "Type";
	}
	
	public String getType() {
		return type;
	}
	
}