package ql2.ast.expression.logic;

import ql2.BaseVisitor;
import ql2.ast.expression.Expr;
import ql2.ast.expression.UnaryExpr;

/**
 * 
 * @author felixbarten
 * Not Expr !Expr
 */
public class Not extends UnaryExpr {

	public Not(Expr result) {
		super(result);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
