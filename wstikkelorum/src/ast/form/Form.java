package ast.form;

import ast.TreeNode;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Form extends TreeNode implements Visitable {
	private final String identifier;
	private final Body body;

	public Form(int lineNumber, String identifier, Body result) {
		super(lineNumber);
		this.identifier = identifier;
		this.body = result;
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
