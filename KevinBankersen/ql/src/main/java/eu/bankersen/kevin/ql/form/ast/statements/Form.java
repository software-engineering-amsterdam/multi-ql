package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.Node;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitable;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class Form extends Node implements Visitable {

	private final String name;
	private final Body body;

	public Form(String name, Body body, int line) {
		super(line);
		this.name = name;
		this.body = body;
	}

	public Body body() {
		return body;
	}

	public String name() {
		return name;
	}

	@Override
	public <T> void accept(Visitor<T> visitor, T context) {
		visitor.visit(this, context);
	}

}
