package ql.ast.statement;

import ql.ast.ASTNode;
import ql.ast.statement.question.Question;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class Statement extends ASTNode implements Visitable {
	private Question question;
	private IfStatement ifStatement;

	public Statement(Question question) {
		super(question.getLineNumber());
		this.question = question;
	}

	public Statement(IfStatement ifStatement) {
		super(ifStatement.getLineNumber());
		this.ifStatement = ifStatement;
	}

	public Question getQuestion() {
		return question;
	}

	public IfStatement getIfStatement() {
		return ifStatement;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
