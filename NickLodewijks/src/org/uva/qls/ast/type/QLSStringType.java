package org.uva.qls.ast.type;

public final class QLSStringType extends QLSType {

	public QLSStringType() {
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLSStringType;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "String";
	}
}
