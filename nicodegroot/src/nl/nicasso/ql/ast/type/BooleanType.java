package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class BooleanType extends Type {

	private final String type;

	public BooleanType() {
		super();
		this.type = "Boolean";
	}

	public String getType() {
		return type;
	}
	
}
