package nl.nicasso.ql.ast.expressions;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public abstract class Expression extends ASTNode {

	protected Type currentType;
	
	public Expression(CodeLocation location) {
		super(location);
	}
	
	public Type getType() {
		return this.currentType; 
	}
	
	public abstract <T> T accept(ExpressionVisitor<T> visitor);
	
}