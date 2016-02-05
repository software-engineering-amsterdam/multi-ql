package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.literal.Literal;

public class LiteralExpr extends Expr {

	private Literal<?> literal;

	public LiteralExpr(Literal<?> literal) {
		this.literal = literal;
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

	@Override
	public Result validate() {
		return Result.TRUE();
	}
}
