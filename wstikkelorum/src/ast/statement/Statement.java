package ast.statement;

import ast.TreeNode;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Statement extends TreeNode implements Visitable {
	private Question question;
	private AssignmentQuestion assignmentQuestion;
	private IfStatement ifStatement;

	public Statement(Question result) {
		super(result.getLineNumber());
		this.question = result;
	}

	public Statement(AssignmentQuestion result) {
		super(result.getLineNumber());
		this.assignmentQuestion = result;
	}

	public Statement(IfStatement result) {
		super(result.getLineNumber());
		this.ifStatement = result;
	}

	public Question getQuestion() {
		return question;
	}

	public AssignmentQuestion getAssignmentQuestion() {
		return assignmentQuestion;
	}

	public IfStatement getIfStatement() {
		return ifStatement;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
