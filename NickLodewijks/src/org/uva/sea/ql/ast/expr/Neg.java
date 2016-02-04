package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.TypeChecker;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;

public class Neg extends AbstractUnaryExpr {

	public Neg(Expr expr) {
		super(expr);
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
	public void _accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {
		Result result;

		result = TypeChecker.checkType(expr, ValueType.INTEGER);
		if (result.isFalse()) {
			return result;
		}

		return Result.TRUE();
	}
}
