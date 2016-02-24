package nl.nicasso.ql.ast.type;

import nl.nicasso.ql.TypeChecker;

public class MoneyType extends NumericType {

	String type;

	public MoneyType() {
		super();
		this.type = "Money";
	}

	public String getType() {
		return type;
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
}
