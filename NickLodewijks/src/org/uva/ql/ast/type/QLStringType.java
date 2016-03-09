package org.uva.ql.ast.type;

import org.uva.ql.ast.ASTSourceInfo;

public final class QLStringType extends QLType {

	public QLStringType() {
		super(null);
	}

	public QLStringType(ASTSourceInfo context) {
		super(context);
	}

	@Override
	public <T, U> T accept(QLTypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLStringType;
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
