package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.StatementVisitor;

public class ComputedQuestion extends Question {

	private final Expression expression;

	public ComputedQuestion(Identifier identifier, String label, Type type, Expression expression, CodeLocation location) {
		super(identifier, label, type, location);
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}
