package nl.nicasso.ql.ast.expressions;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.StringType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class Parenthesis extends Unary {

	private final Expression expr;

	public Parenthesis(Expression expr, CodeLocation location) {
		super(location);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
		return "(" + expr + ")";
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Type inferType(Type exprType) {
		if (exprType.equals(new BooleanType()) || exprType.equals(new IntegerType()) || exprType.equals(new MoneyType()) || exprType.equals(new StringType())) {
			return exprType;
		}

		return new UnknownType();		
	}
}
