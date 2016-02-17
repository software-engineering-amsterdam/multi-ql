package ql.ast;

import java.util.List;

public class Block {
	private List<Statement> statements;

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
}
