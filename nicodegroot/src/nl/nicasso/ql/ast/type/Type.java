package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class Type extends ASTNode implements Traversable {
	
	String type;
	
	public Type() {
		this.type = "Type";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public String getType() {
		return type;
	}
	
}