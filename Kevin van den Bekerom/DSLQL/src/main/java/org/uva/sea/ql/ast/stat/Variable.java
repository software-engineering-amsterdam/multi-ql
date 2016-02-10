package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.Type;

public class Variable implements Visitable {
	String identifier;
	Type type;
	
	public Variable(String identifier, Type type) {
		this.identifier = identifier;
		this.type = type;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
