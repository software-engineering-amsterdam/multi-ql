package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;

public class Div extends ArithmeticExpr {

	public Div(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Integer interpret(Context context) {
		return (Integer) lhs.interpret(context) / (Integer) rhs.interpret(context);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
