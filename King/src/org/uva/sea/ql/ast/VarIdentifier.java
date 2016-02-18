package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VarIdentifier extends ASTNODE{
	private final String name;
	
	private ValueType type;

	public VarIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		visitor.visit(this);
		
	}
}
