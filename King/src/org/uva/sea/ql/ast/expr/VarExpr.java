package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class VarExpr extends Expr{
	private final VarIdentifier identifier;
	
	public VarExpr(VarIdentifier identifier) {
		this.identifier =  identifier;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}
	
	public Type type() {
		return identifier.getType();
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
