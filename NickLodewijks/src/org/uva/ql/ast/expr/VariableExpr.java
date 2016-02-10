package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.VariableIdentifier;

public class VariableExpr extends Expr {
	private final VariableIdentifier identifier;

	public VariableExpr(VariableIdentifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public Object interpret(Context context) {
		return context.getValue(identifier.getName());
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);

		identifier.accept(visitor);
	}

	@Override
	public ValueType type() {
		return identifier.getType();
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
