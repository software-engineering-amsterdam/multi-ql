package org.uva.ql.ast.type;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public final class QLBooleanType extends QLType {

	public QLBooleanType() {
		super(null);
	}

	public QLBooleanType(ParserRuleContext context) {
		super(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
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
}
