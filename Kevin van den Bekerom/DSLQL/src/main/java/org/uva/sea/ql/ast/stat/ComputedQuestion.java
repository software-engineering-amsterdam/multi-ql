package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.Type;

public class ComputedQuestion extends Question {
	private Expr expr;
	
	public ComputedQuestion(String identifier, String label, Type type, Expr expr, int startLine) {
		super(identifier, label, type, startLine);
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return this.expr;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	
	@Override
	public String toString() {
		return "Computed " + super.toString();
	}
}
