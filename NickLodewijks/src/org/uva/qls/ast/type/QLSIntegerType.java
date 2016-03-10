package org.uva.qls.ast.type;

public final class QLSIntegerType extends QLSType {

	public QLSIntegerType() {

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLSIntegerType;
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
