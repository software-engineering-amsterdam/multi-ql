package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.Visitor;
import org.uva.sea.ql.ast.expr.*;

public class Question extends Stat {
	private String identifier;
	private String label;
	private Type type;
	private List<Expr> computedResult; // <==> Maybe Expr

	public Question(String identifier, String label, Type type, Expr expr) {
		this.identifier = identifier;
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
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Type getType() {
		return this.type;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
