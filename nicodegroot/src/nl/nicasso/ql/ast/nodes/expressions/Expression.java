package nl.nicasso.ql.ast.nodes.expressions;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public abstract class Expression extends ASTNode {

	private Type currentType;

	public Expression(CodeLocation location) {
		super(location);
	}

	public Type getType() {
		return this.currentType;
	}

	public abstract <T, U> T accept(ExpressionVisitor<T, U> visitor, U context);

}