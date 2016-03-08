package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.literal.Variable;
import ql.ast.visitor.Visitor;

public class ComputedQuestion extends Question {
	private final Expression expression;

	public ComputedQuestion(int lineNumber, Variable variable, String str, Expression expression) {
		super(lineNumber, variable, str);
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
