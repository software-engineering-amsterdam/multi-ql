package uva.ql.ast.questions;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.variables.Variable;

public abstract class Question<T> extends Node {

	private String label;
	private Variable<T> variable;
	
	public Question(Node parent, String label, Variable<T> variable, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.label = label;
		this.variable = variable;
	}
	
	abstract public EnumType getType();
	
	public String getLabel() {
		return this.label;
	}
	
	public Variable<T> getVariable() {
		return this.variable;
	}
	
	public void setVariable(Variable<T> var) {
		this.variable = var;
	}
}
