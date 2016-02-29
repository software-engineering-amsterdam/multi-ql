package ql.ast;

import java.util.List;

public class Block {
	private List<Statement> statements;
	private List<Question> questions;
	
	public Block(List<Statement> statements, List<Question> questions) {
		// TODO Auto-generated constructor stub
		setStatements(statements);
		setQuestions(questions);
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
