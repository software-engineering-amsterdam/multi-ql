package org.uva.ql.ast.expr;

import org.uva.ql.TypeChecker;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;

public class Not extends Expr {

	private final Expr expr;

	public Not(Expr expr) {
		this.expr = expr;
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
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	public Expr getExpr() {
		return expr;
	}
}
