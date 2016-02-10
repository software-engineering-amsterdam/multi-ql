package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Type;

public class Variable {
	String identifier;
	Type type;
	
	public Variable(String identifier, Type type) {
		this.identifier = identifier;
		this.type = type;
	}
}
