package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.Expr;
import ql2.ast.UnaryExpr;

/**
 * 
 * @author felixbarten
 * Negative Expr -Expr
 */
public class Negative extends UnaryExpr {

	public Negative(Expr result) {
		super(result);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
