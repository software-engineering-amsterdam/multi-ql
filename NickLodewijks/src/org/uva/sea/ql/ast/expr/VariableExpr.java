package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VariableIdentifier;

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
