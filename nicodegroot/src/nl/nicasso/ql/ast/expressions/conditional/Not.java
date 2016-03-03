package nl.nicasso.ql.ast.expressions.conditional;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.expressions.Unary;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Not extends Unary {
	
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
	
	@Override
	public Type inferType(Type exprType) {
		if (exprType.equals(new BooleanType())) {
			return exprType;
		}

		return new UnknownType();		
	}
}
