package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class Neg extends Expr {

	private final Expr expr;

	public Neg(Expr expr) {
		this.expr = expr;
	}

	@Override
	public Integer interpret(Context context) {
		return -Math.abs((Integer) expr.interpret(context));
	}

	@Override
	public ValueType type() {
		return ValueType.INTEGER;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	public Expr getExpr() {
		return expr;
	}
}
