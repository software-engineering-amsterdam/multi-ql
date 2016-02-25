package ast.statement;

import ast.expression.Expression;
import ast.literal.Variable;
import ast.visitor.Visitor;

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
