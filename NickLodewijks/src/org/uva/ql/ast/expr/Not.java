package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public class Not extends UnaryExpr {

	public Not(ParserRuleContext context, Expr expr) {
		super(context, expr);
	}

	@Override
	public Boolean interpret(Context context) {
		return !(Boolean) expr().interpret(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
