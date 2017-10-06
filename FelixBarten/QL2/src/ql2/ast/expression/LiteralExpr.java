package ql2.ast.expression;

import ql2.ast.Expr;
import ql2.ast.UnaryExpr;
import ql2.ast.literal.Literal;

public class LiteralExpr extends Expr {
	
	Literal literal;

	public LiteralExpr(Literal result) {
		// TODO Auto-generated constructor stub
		setLiteral(result);
	}

	public Literal getLiteral() {
		return literal;
	}

	public void setLiteral(Literal literal) {
		this.literal = literal;
	}

}
