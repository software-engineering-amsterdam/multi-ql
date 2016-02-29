package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.ASTNode;

public class Type extends ASTNode {
	
	String type;
	
	public Type() {
		this.type = "Type";
	}
	
	public String getType() {
		return type;
	}
	
	public boolean compatibleWith(Type type) {
		switch(type.getType()) {
			case "Boolean":
				return true;
			case "Integer":
				return true;
			case "Money":
				return true;
			case "String":
				return true;
			default:
				return false;
		}
	}
}