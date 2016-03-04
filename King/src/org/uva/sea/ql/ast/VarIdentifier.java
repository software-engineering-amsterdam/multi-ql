package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.type.Type;
//Remove the get types
public class VarIdentifier{
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
	public String toString() {
		return "[ name: "+name+" -> type: "+type+" ]";
	}

}
