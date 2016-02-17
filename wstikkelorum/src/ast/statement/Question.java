package ast.statement;

import ast.literal.Variable;
import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Question implements Visitable{
	private Variable variable;
	private String str;
	
	public Question(Variable variable, String str) {
		this.variable = variable;
		this.str = str;
	}
	
	public Variable getVariable() {
		return variable;
	}


	public String getStr() {
		return str;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
