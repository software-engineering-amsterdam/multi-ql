package org.uva.ql.ast.literal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public final class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(ParserRuleContext context, boolean value) {
		super(context, value);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
