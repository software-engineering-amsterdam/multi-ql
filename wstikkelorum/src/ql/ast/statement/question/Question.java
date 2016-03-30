package ql.ast.statement.question;

import ql.ast.ASTNode;
import ql.ast.literal.Variable;
import ql.ast.visitor.Visitable;
import ql.ast.visitor.Visitor;

public class Question extends ASTNode implements Visitable {
	private final Variable variable;
	private final String questionString;

	public Question(int lineNumber, Variable variable, String questionString) {
		super(lineNumber);
		this.variable = variable;
		this.questionString = questionString;
	}

	public Variable getVariable() {
		return variable;
	}

	public String getQuestionString() {
		return questionString;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
