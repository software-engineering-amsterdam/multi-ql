package ql.ast;

import org.antlr.v4.runtime.Token;

public class Question {
	private String question;
	private String name;
	private QuestionType type;
	
	public Question (String q_text, String identity, QuestionType typ) {
		this.setQuestion(q_text);
		this.setName(identity);
		this.setType(typ);
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String q_text) {
		this.question = q_text;
	}

	public String getName() {
		return name;
	}

	public void setName(String identity) {
		this.name = identity;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
}
