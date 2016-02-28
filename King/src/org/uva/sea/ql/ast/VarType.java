package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public class VarType extends ASTNode{
	private final String name;

	public VarType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*public Type getType() {
		return ValueType.getByName(this.name);
	}*/

	@Override
	public Type accept(QLNodeVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
		
	}
}
