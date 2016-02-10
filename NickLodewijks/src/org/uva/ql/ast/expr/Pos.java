package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;

public class Pos extends AbstractUnaryExpr {

	public Pos(Expr expr) {
		super(expr);
	}

	@Override
	public Integer interpret(Context context) {
		return Math.abs((Integer) expr.interpret(context));
	}

	@Override
	public ValueType type() {
		return ValueType.INTEGER;
	}

	@Override
	public void _accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {
		if (expr.type() != ValueType.INTEGER) {
			return Result.FALSE("Expression " + expr + " should be of type " + ValueType.INTEGER + " but is "
					+ expr.type());
		}

		return Result.TRUE();
	}
}
