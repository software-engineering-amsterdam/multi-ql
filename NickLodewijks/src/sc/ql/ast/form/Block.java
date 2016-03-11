package sc.ql.ast.form;

import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.ql.ast.stat.IFStatement;
import sc.ql.ast.stat.Question;

public class Block extends ASTNode {

	private final List<Question> questions;
	private final List<IFStatement> statements;

	public Block(List<Question> questions, List<IFStatement> statements) {
		this.questions = questions == null ? Collections.emptyList() : questions;
		this.statements = statements == null ? Collections.emptyList() : statements;
	}

	public List<Question> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<IFStatement> getIfStatements() {
		return Collections.unmodifiableList(statements);
	}

	public <T, U> T accept(FormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
