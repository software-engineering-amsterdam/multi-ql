package nl.nicasso.ql.ast.expression.conditional;

import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Monomial;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;

public class Not extends Monomial implements Traversable  {
	
	private final Expression expr;

	public Not(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "!" + expr;
	}
	
	public Type checkAllowedTypes(Type expr) {
		if (expr.getType().equals(new BooleanType().getType())) {
			return expr;
		}

		return null;		
	}
}
