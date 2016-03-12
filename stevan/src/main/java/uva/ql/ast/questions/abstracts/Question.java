package uva.ql.ast.questions.abstracts;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.variables.abstracts.Variable;

public abstract class Question extends Node {

	private String label;
	private Variable variable;
	
	public Question(Node parent, String label, Variable variable, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.label = label;
		this.variable = variable;
	}
	
	abstract public EnumType getType();
	
	public String getLabel() {
		return this.label;
	}
	
	public Variable getVariable() {
		return this.variable;
	}
	
	public void setVariable(Variable var) {
		this.variable = var;
	}
}
