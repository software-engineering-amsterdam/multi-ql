package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.*;

public class Question extends Stat {
	private Variable variable;
	private String label;
	private Type type;
	private List<Expr> computedResult; //Grammar equivalent to Expr*

	public Question(Variable variable, String label, Type type, Expr expr) {
		this.variable = variable;
		this.label = label;
		this.type = type;
		computedResult = new ArrayList<Expr>();
		if (expr != null) {
			computedResult.add(expr);
		}
	}
	
	public List<Expr> getComputedResult() {
		return computedResult;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
