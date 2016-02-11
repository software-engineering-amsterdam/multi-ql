package ast.statement;

import ast.Visitable;
import ast.Visitor;

public class Statement implements Visitable{
	private Question question;
	private AssignmentQuestion assignmentQuestion;
	private IfStatement ifStatement;

	public Statement(Question result) {
		this.question = result;
	}

	public Statement(AssignmentQuestion result) {
		this.assignmentQuestion = result;
	}

	public Statement(IfStatement result) {
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
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
