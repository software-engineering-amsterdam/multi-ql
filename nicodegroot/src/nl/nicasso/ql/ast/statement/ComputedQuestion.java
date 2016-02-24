package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.type.Type;

public class ComputedQuestion extends Question {

	Expression expr;
	
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
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}

}
