package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.Expr;
import ql2.ast.UnaryExpr;

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
