package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class VarDeclaration extends ASTNode{
	private final Type type;
	private final VarIdentifier identifier;

	public VarDeclaration(Type type, VarIdentifier identifier) {
		this.identifier = identifier;
		this.type = type;
	}

	public VarIdentifier getIdentifier() {
		return identifier;
	}

	public Type getType() {
		return type;
	}

	@Override
	public Type accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
		
	}

	
}
