package ast.model.statement;

import ast.model.Expression;
import ast.model.literal.Identifier;
import ast.model.type.Type;
import ast.visitor.StatementVisitor;

public class ComputedQuestion extends Question {
	private Expression expression;
	
	public ComputedQuestion(Identifier identifier, String label, Type type, Expression expression, int line) {
		super(identifier, label, type, line);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	@Override
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);
	}
}
