package org.uva.ql.ast.form;

import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;

public class QLBlock extends ASTNode {

	private final List<QLQuestion> questions;
	private final List<QLIFStatement> statements;

	public QLBlock(List<QLQuestion> questions, List<QLIFStatement> statements) {
		this.questions = questions == null ? Collections.emptyList() : questions;
		this.statements = statements == null ? Collections.emptyList() : statements;
	}

	public List<QLQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<QLIFStatement> getIfStatements() {
		return Collections.unmodifiableList(statements);
	}

	public <T, U> T accept(QLFormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
