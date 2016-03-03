package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;

public final class QLQuestionComputed extends QLQuestion {

	public QLQuestionComputed(ParserRuleContext context, QLType type, String id, String label, Expr condition,
			Expr calculation) {
		super(context, type, id, label, condition, calculation);

	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
