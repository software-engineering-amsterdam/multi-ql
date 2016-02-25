package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public class MoneyType extends NumericType {

	private final String type;

	public MoneyType() {
		super();
		this.type = "Money";
	}

	public String getType() {
		return type;
	}
}
