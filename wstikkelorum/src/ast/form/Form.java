package ast.form;

import ast.TreeNode;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Form extends TreeNode implements Visitable {
	private String id;
	private Body body;

	public Form(int lineNumber, String id, Body result) {
		super(lineNumber);
		this.id = id;
		this.body = result;
	}

	public String getId() {
		return id;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
