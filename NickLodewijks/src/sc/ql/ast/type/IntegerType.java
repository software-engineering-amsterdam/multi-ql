package sc.ql.ast.type;

public final class IntegerType extends ValueType {

	public IntegerType() {

	}

	@Override
	public <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntegerType;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "Integer";
	}

}
