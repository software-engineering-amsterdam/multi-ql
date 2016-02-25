package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class VarIdentifier extends ASTNode{
	private final String name;
	
	private Type type;

	public VarIdentifier(String name) {
		this.name = name;
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
	public void accept(QLNodeVisitor visitor) {
		
		
	}
}
