package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.visit.Visitor;
import org.uva.sea.ql.type.Type;

public class Question extends ASTNode {
	protected String identifier;
	protected String label;
	protected Type type;

	public Question(String identifier, String label, Type type, int startLine) {
		super.startLine = startLine;
		this.identifier = identifier;
		this.label = label;
		this.type = type;
		
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Type getType() {
		return this.type;
	}

	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	
	@Override
	public String toString() {
		return "Question " + identifier + " of type " + type.toString();
	}
}
