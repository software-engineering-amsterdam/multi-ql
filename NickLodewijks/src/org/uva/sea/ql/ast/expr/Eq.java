package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;

public class Eq extends AbstractBinaryExpr {

	public Eq(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return lhs.interpret(context).equals(rhs.interpret(context));
	}

	@Override
	public ValueType type() {
		return ValueType.BOOLEAN;
	}

	@Override
	public void _accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {

		if (lhs.type() == rhs.type()) {
			return Result.TRUE();
		}

		String msg;

		msg = String.format(
				"[%s: %s] Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
				getLineIndex(), getCharIndex(), lhs.type().getName(), rhs.type().getName());

		return Result.FALSE(msg);
	}
}
