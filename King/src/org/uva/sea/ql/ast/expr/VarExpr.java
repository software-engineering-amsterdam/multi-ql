package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VarExpr extends Expr{
	private final VarIdentifier identifier;
	
	public VarExpr(VarIdentifier identifier) {
		this.identifier =  identifier;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}
	
	public ValueType type() {
		return identifier.getType();
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
