package org.uva.sea.ql.ast;

public class Question {
	private final VARIDENTIFIER identifier;
	private final String text;

	public Question(VARIDENTIFIER identifier, String text) {
		this.identifier = identifier;
		this.text = text;
	}

	public VARIDENTIFIER getVariableId() {
		return identifier;
	}

	public String getText() {
		return text;
	}
}
