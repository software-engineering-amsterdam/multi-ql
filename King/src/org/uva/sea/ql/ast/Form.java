package org.uva.sea.ql.ast;

public class Form {
	private String id;
	private Block body;

	public Form(String id, Block body) {
		this.id = id;
		this.body = body;
	}

	public String getName() {
		return id;
	}
}
