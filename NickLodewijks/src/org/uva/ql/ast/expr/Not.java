package org.uva.ql.ast.expr;

import org.uva.ql.TypeChecker;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;

public class Not extends AbstractUnaryExpr {

	public Not(Expr expr) {
		super(expr);
	}

	@Override
	public Boolean interpret(Context context) {
		return !(Boolean) expr.interpret(context);
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
		Result result;

		result = TypeChecker.checkType(expr, ValueType.BOOLEAN);
		if (result.isFalse()) {
			return result;
		}

		return Result.TRUE();
	}

}
