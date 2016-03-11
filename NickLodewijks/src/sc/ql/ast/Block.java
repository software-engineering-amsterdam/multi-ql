package sc.ql.ast;

import java.util.Collections;
import java.util.List;

import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.Question;

public class Block extends ASTNode {

	private final List<Question> questions;
	private final List<IfThen> statements;

	public Block(List<Question> questions, List<IfThen> statements) {
		this.questions = questions == null ? Collections.emptyList() : questions;
		this.statements = statements == null ? Collections.emptyList() : statements;
	}

	public List<Question> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<IfThen> getIfStatements() {
		return Collections.unmodifiableList(statements);
	}

	public <T, U> T accept(FormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
