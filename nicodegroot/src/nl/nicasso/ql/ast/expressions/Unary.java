package nl.nicasso.ql.ast.expressions;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.Type;

public abstract class Unary extends Expression {

	public Unary(CodeLocation location) {
		super(location);
	}

	Expression expr;

	public Expression getExpr() {
		return expr;
	}
	
	public abstract Type inferType(Type exprType);
}
