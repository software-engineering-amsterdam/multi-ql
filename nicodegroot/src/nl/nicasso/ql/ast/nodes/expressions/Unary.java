package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.Type;

public abstract class Unary extends Expression {

	private Expression expr;
	
	public Unary(CodeLocation location) {
		super(location);
	}

	public Expression getExpr() {
		return expr;
	}
	
	public abstract Type inferType(Type exprType);
}
