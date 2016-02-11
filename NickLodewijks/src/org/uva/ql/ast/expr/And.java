package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class And extends BinaryExpr {

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
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
