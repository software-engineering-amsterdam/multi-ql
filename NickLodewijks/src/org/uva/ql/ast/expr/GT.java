package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;

public class GT extends BinaryExpr {

	public GT(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return (Integer) lhs.interpret(context) > (Integer) rhs.interpret(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
