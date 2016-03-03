package nl.nicasso.ql.ast.expression.conditional;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Monomial;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.ExpressionVisitor;

public class Not extends Monomial {
	
	private final Expression expr;

	public Not(Expression expr, CodeLocation location) {
		super(location);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "!" + expr;
	}
	
	public Type checkAllowedTypes(Type expr) {
		if (expr.equals(new BooleanType())) {
			return expr;
		}

		return null;		
	}
}
