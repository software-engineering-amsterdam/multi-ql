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

	public VariableIdentifier getVariableId() {
		return identifier;
	}

	@Override
	public Object interpret(Context context) {
		Object value;

		value = context.getValue(identifier.getName());

		switch (type()) {
		case BOOLEAN:
			return (value != null ? (Boolean) value : Boolean.FALSE);

		case INTEGER:
			return (value != null ? (Integer) value : 0);

		case STRING:
			return (value != null ? (String) value : "");
		default:
			return value;
		}
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
}
