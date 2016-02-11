package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VarIdentifier;

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

}
