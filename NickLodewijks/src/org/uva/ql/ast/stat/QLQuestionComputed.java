package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;

public class QLQuestionComputed extends QLQuestion {

	private final Expr expr;

	public QLQuestionComputed(ParserRuleContext context, QLType type, String id, String label, Expr expression) {
		super(context, type, id, label);
		this.expr = expression;
	}

	public Expr expr() {
		return expr;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
