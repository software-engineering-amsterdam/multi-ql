package nl.uva.ql.ast.statement;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.type.Type;
import nl.uva.ql.visitors.StatementVisitor;

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
	public <T> T accept(StatementVisitor<T> statementVisitor){
		return statementVisitor.visit(this);
	}
}
