package sc.qls.ast.type;

public final class QLSBooleanType extends QLSType {

	public QLSBooleanType() {

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLSBooleanType;
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
