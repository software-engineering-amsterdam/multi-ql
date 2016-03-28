package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class VarExpr extends Expr {
	private final VarIdentifier identifier;

	public VarExpr(VarIdentifier identifier) {
		this.identifier = identifier;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}
	
	public String getIdentifierName() {
		return identifier.getName();
	}

	public Type type() {
		return identifier.getType();
	}

	public boolean equals(VarExpr varExpr) {
		return identifier.getName().equals(varExpr.getIdentifier().getName());
	}

	@Override
	public String toString() {
		return "[ " + identifier.toString() + " ]";
	}

	@Override
	public <T> T accept(QLNodeVisitor<T> visitor) {
		return visitor.visit(this);

	}

}
