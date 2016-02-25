package org.uva.ql.ast.type;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public final class StringType extends VariableType {

	public StringType() {
		super(null);
	}

	public StringType(ParserRuleContext context) {
		super(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StringType;
	}

	@Override
	public int hashCode() {
		return 42;
	}
}
