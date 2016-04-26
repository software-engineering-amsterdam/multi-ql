package ql.ast;

import org.antlr.v4.runtime.Token;

import ql.ASTNode;

public abstract class Question extends ASTNode {
	private String question_text;
	private String question_name;
	private QuestionType question_type;
	
	public Question (String q_text, String identity, QuestionType typ) {

	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public String getQuestion_name() {
		return question_name;
	}

	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}

	public QuestionType getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(QuestionType question_type) {
		this.question_type = question_type;
	}
	
}



