package nl.nicasso.ql.ast.nodes.types;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.UnknownValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class UnknownType extends Type {

	public UnknownType() {

	}

	public UnknownType(CodeLocation location) {
		super(location);
	}

	@Override
	public UnknownValue getDefaultValue() {
		return new UnknownValue();
	}

	@Override
	public String getTypeName() {
		return "Unknown";
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
		new AssertionError("UnknownType does not have an accept method.");
		return null;
	}
}