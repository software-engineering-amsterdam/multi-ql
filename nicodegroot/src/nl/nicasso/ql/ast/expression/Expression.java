package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.ExpressionVisitor;

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