package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;

public final class QLQuestionInput extends QLQuestion {

	public QLQuestionInput(ASTSourceInfo context, QLType type, String id, String label, Expr condition) {
		super(context, type, id, label, condition, null);
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
