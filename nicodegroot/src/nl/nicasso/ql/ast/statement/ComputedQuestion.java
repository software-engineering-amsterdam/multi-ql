package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.type.Type;
import nl.nicasso.ql.visitor.Visitor;

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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
