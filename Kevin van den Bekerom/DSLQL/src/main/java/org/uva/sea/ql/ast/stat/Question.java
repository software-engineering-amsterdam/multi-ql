package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.*;

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

	public void accept(Visitor visitor) {
		visitor.visit(this, null);
	}
	
	@Override
	public String toString() {
		return "Question " + identifier + " of type " + type.name();
	}
}
