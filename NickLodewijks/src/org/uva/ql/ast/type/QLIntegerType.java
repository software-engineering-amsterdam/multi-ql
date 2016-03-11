package org.uva.ql.ast.type;

public final class QLIntegerType extends QLType {

	public QLIntegerType() {

	}

	@Override
	public <T, U> T accept(QLTypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLIntegerType;
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
