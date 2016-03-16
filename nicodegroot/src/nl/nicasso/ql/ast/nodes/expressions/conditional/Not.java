package nl.nicasso.ql.ast.nodes.expressions.conditional;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.Unary;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
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
