package org.uva.ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.stat.IFStat;

public class Block extends ASTNode {
	private List<Question> questions;
	private List<IFStat> statements;

	public Block() {
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFStat>();
	}

	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFStat statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<IFStat> getIfStatements() {
		return statements;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
