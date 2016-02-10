package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class LT extends BinaryExpr {

	public LT(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return (Integer) lhs.interpret(context) < (Integer) rhs.interpret(context);
	}

	@Override
	public ValueType type() {
		return ValueType.BOOLEAN;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
