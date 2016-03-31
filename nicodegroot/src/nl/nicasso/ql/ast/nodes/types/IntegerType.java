package nl.nicasso.ql.ast.nodes.types;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class IntegerType extends Type {

	public IntegerType() {

	}

	public IntegerType(CodeLocation location) {
		super(location);
	}

	@Override
	public IntegerValue getDefaultValue() {
		return new IntegerValue(0);
	}

	@Override
	public String getTypeName() {
		return "Integer";
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
