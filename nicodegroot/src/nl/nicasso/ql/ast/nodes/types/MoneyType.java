package nl.nicasso.ql.ast.nodes.types;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class MoneyType extends Type {

	public MoneyType() {

	}

	public MoneyType(CodeLocation location) {
		super(location);
	}

	@Override
	public MoneyValue getDefaultValue() {
		return new MoneyValue(new BigDecimal(0.00));
	}

	@Override
	public String getTypeName() {
		return "Money";
	}

	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof Type)) {
			return false;
		}
		Type t2 = (Type) ob;
		return getClass().equals(t2.getClass());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
