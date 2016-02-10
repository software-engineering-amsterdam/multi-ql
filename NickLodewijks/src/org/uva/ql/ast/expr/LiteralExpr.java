package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.literal.Literal;

public class LiteralExpr extends Expr {

	private Literal<?> literal;

	public LiteralExpr(Literal<?> literal) {
		this.literal = literal;
	}

	public Literal<?> getLiteral() {
		return literal;
	}

	@Override
	public Object interpret(Context context) {
		return literal.getValue();
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public ValueType type() {
		return literal.type();
	}
}
