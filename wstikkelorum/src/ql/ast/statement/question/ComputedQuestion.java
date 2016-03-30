package ql.ast.statement.question;

import ql.ast.expression.Expression;
import ql.ast.literal.Variable;
import ql.ast.visitor.Visitor;

public class ComputedQuestion extends Question {
	private final Expression expression;

	public ComputedQuestion(int lineNumber, Variable variable, String questionString, Expression expression) {
		super(lineNumber, variable, questionString);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
