package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.TypeChecker;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;

public class And extends AbstractBinaryExpr {

	public And(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public Boolean interpret(Context context) {
		return (Boolean) lhs.interpret(context) && (Boolean) rhs.interpret(context);
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

		result = TypeChecker.checkType(lhs, ValueType.BOOLEAN);
		if (result.isFalse()) {
			return result;
		}

		result = TypeChecker.checkType(rhs, ValueType.BOOLEAN);
		if (result.isFalse()) {
			return result;
		}

		return Result.TRUE();
	}
}
