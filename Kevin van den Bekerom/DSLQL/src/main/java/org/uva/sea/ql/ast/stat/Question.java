package org.uva.sea.ql.ast.stat;

import java.util.List;

import org.uva.sea.ql.ast.ASTID;
import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.*;

public class Question extends Stat {
	private Variable variable;
	private String label;
	private Type type;
	private List<Expr> computedResult; //Grammar equivalent to Expr*

	public Question(Variable variable, String label, Type type) {
		super(ASTID.QUESTION);
		this.variable = variable;
		this.label = label;
		this.type = type;
	}
	
	public List<Expr> getComputedResult() {
		return computedResult;
	}

	public void setComputedResult(List<Expr> computedResult) {
		this.computedResult = computedResult;
	}
	
	public void accept(Visitor visitor) {
		
	}
}
