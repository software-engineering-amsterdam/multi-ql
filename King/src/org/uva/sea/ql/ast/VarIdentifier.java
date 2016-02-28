package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VarIdentifier extends ASTNode{
	private final String name;
	
	private Type type;

	public VarIdentifier(String name) {
		this.name = name;
	}
	
	public VarIdentifier(String name,Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public Type accept(QLNodeVisitor visitor) {
		return null;
		
	}
}
