package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

public class Block {
	private List<VARDECLARATION> variables;
	private List<Question> questions;
	private List<IFBLOCK> statements;

	public Block() {
		variables = new ArrayList<VARDECLARATION>();
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFBLOCK>();
	}

	public void add(VARDECLARATION var) {
		variables.add(var);
	}

	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFBLOCK statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}
}
