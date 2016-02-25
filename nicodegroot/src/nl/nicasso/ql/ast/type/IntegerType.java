package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class IntegerType extends NumericType {

	private final String type;

	public IntegerType() {
		super();
		this.type = "Integer";
	}

	public String getType() {
		return type;
	}
}
