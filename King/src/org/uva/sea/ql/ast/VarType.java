package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VarType extends ASTNode{
	private final String name;

	public VarType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return ValueType.getByName(this.name);
	}

	@Override
	public void accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
