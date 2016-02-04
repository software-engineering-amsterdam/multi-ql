package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.TypeChecker;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;

public abstract class ArithmeticExpr extends AbstractBinaryExpr {

	public ArithmeticExpr(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Integer interpret(Context context) {
		return (Integer) lhs.interpret(context) + (Integer) rhs.interpret(context);
	}

	@Override
	public ValueType type() {
		return ValueType.INTEGER;
	}

	@Override
	public Result validate() {
		Result result;

		result = TypeChecker.checkType(lhs, ValueType.INTEGER);
		if (result.isFalse()) {
			return result;
		}

		result = TypeChecker.checkType(rhs, ValueType.INTEGER);
		if (result.isFalse()) {
			return result;
		}

		return Result.TRUE();
	}
}
