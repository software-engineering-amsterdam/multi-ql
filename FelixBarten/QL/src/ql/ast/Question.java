package ql.ast;

import org.antlr.v4.runtime.Token;

public class Question {
	private Token question;
	private Token name;
	private QuestionType type;
	
	public Question (Token q_text, Token identity, QuestionType typ) {
		this.setQuestion(q_text);
		this.setName(identity);
		this.setType(typ);
	}

	public Token getQuestion() {
		return question;
	}

	public void setQuestion(Token q_text) {
		this.question = q_text;
	}

	public Token getName() {
		return name;
	}

	public void setName(Token identity) {
		this.name = identity;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}
}
