package ast.statement;

public class Statement {
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
}
