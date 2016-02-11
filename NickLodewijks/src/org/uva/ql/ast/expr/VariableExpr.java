package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.VariableIdentifier;

public class VariableExpr extends Expr {

	private final VariableIdentifier identifier;

	public VariableExpr(VariableIdentifier identifier) {
		this.identifier = identifier;
	}

	public VariableIdentifier getVariableId() {
		return identifier;
	}

	@Override
	public ValueType type() {
		return identifier.getType();
	}

	@Override
	public Object interpret(Context context) {
		Object value;

		value = context.getValue(identifier.getName());

		return value;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
