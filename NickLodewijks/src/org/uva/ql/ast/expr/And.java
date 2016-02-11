package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;

public class And extends BinaryExpr {

	public And(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return (Boolean) lhs.interpret(context) && (Boolean) rhs.interpret(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
