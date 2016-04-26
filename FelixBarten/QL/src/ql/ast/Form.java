package ql.ast;

import java.util.List;

public class Form {
	// keep the list of questions?
	private List<Question> questions;
	private Block content;

	public Form(Object object, Block result) {
		// TODO Auto-generated constructor stub
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Block getContent() {
		return content;
	}

	public void setContent(Block content) {
		this.content = content;
	} 
}
