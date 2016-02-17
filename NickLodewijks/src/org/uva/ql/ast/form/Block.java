package org.uva.ql.ast.form;

import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.stat.IFStat;

public class Block extends ASTNode {
	private final List<Question> questions;
	private final List<IFStat> statements;

	public Block(List<Question> questions, List<IFStat> statements) {
		this.questions = questions;
		this.statements = statements;
	}

	public List<Question> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<IFStat> getIfStatements() {
		return Collections.unmodifiableList(statements);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
