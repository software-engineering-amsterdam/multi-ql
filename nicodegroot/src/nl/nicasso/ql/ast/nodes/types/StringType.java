package nl.nicasso.ql.ast.nodes.types;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.visitors.TypeVisitor;

public class StringType extends Type {

	public StringType() {

	}

	public StringType(CodeLocation location) {
		super(location);
	}

	@Override
	public StringValue getDefaultValue() {
		return new StringValue("");
	}

	@Override
	public String getTypeName() {
		return "String";
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
