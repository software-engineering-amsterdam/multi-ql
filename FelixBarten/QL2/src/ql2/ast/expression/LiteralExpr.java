package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.Expr;
import ql2.ast.UnaryExpr;
import ql2.ast.literal.Literal;

public class LiteralExpr extends Expr {
	
	Literal literal;

	public LiteralExpr(Literal result) {
		setLiteral(result);
	}

	public Literal getLiteral() {
		return literal;
	}

	public void setLiteral(Literal literal) {
		this.literal = literal;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
