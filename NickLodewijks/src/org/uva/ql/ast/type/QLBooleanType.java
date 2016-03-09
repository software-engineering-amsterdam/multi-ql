package org.uva.ql.ast.type;

import org.antlr.v4.runtime.ParserRuleContext;

public final class QLBooleanType extends QLType {

	public QLBooleanType() {
		super(null);
	}

	public QLBooleanType(ParserRuleContext context) {
		super(context);
	}

	@Override
	public <T, U> T accept(QLTypeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof QLBooleanType;
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
