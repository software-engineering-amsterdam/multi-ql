package sc.ql.ast.type;

public final class BooleanType extends ValueType {

	public BooleanType() {

	}

	@Override
	public <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BooleanType;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "Boolean";
	}
}
