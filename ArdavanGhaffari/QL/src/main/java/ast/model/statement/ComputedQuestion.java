package ast.model.statement;

import ast.model.expression.Expression;
import ast.model.expression.literal.Identifier;
import ast.model.type.Type;

public class ComputedQuestion extends Question {
	private Expression expression;
	
	public ComputedQuestion(Identifier identifier, String label, Type type, Expression expression) {
		super(identifier, label, type);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
}
