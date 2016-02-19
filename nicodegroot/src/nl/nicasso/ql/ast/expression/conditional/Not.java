package nl.nicasso.ql.ast.expression.conditional;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Monomial;
import nl.nicasso.ql.ast.type.Type;

public class Not extends Monomial implements Traversable  {
	
	Expression expr;

	public Not(Expression expr) {
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
