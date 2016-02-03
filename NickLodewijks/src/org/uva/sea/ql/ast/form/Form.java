package org.uva.sea.ql.ast.form;

public class Form {

	private String id;
	private Block body;

	public Form(String id, Block body) {
		this.id = id;
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("Form: " + id);
		sb.append("\n");

		for (Question q : body.getQuestions()) {
			sb.append("  ");
			sb.append(q.toString());
			sb.append("\n");
		}

		return sb.toString();
	}
}
