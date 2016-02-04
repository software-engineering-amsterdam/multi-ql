package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;

public class Sub extends ArithmeticExpr {

	public Sub(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Integer interpret(Context context) {
		return (Integer) lhs.interpret(context) - (Integer) rhs.interpret(context);
	}

	@Override
	public void _accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
