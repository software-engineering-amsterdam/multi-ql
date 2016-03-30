package ql.ast.form;

import ql.ast.ASTNode;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class Form extends ASTNode implements Visitable {
	private final String identifier;
	private final Body body;

	public Form(int lineNumber, String identifier, Body body) {
		super(lineNumber);
		this.identifier = identifier;
		this.body = body;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
