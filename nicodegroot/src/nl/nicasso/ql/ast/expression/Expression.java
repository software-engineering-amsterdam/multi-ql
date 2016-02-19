package nl.nicasso.ql.ast.expression;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.type.Type;

public abstract class Expression extends ASTNode {
	
	protected Type currentType;
	
	public Type getType() {
		return this.currentType; 
	}
	
}