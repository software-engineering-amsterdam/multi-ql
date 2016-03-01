package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.type.Type;

public abstract class Expression extends ASTNode implements Traversable {
	
	public Expression(CodeLocation location) {
		super(location);
	}

	protected Type currentType;
	
	public Type getType() {
		return this.currentType; 
	}
	
}