package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.type.Type;

public class ComputedQuestion extends Question {

	private final Expression expr;
	
	public ComputedQuestion(IdentifierLit id, String label, Type type, Expression expr) {
		super(id, label, type);
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}

}
