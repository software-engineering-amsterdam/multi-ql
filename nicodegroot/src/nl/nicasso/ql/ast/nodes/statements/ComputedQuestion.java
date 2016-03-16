package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.StatementVisitor;

public class ComputedQuestion extends Question {

	private final Expression expr;
	
	public ComputedQuestion(Identifier id, String label, Type type, Expression expr, CodeLocation location) {
		super(id, label, type, location);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
	
}
