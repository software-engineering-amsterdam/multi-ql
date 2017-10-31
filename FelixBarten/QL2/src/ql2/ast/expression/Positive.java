package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.Expr;
import ql2.ast.UnaryExpr;
import ql2.ast.literal.Literal;

public class Positive extends UnaryExpr{

	public Positive(Expr result) {
		super(result);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
