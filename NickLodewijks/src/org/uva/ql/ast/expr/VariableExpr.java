package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableIdentifier;

public class VariableExpr extends Expr {

	private final VariableIdentifier identifier;

	public VariableExpr(ParserRuleContext context, VariableIdentifier identifier) {
		super(context);
		this.identifier = identifier;
	}

	public VariableIdentifier getVariableId() {
		return identifier;
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
