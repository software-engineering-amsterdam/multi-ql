package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;

public class NEq extends BinaryExpr {

	public NEq(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return !lhs.interpret(context).equals(rhs.interpret(context));
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
