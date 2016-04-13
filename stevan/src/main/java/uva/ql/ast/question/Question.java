package uva.ql.ast.question;

import uva.ql.ast.Node;
import uva.ql.ast.variable.Variable;


public abstract class Question extends Node {

	private final String label;
	private final Variable variable;
	
	public Question(Node parent, int startLine, int startColumn, String label, Variable variable) {
		super(parent, startLine, startColumn);
		this.label = label;
		this.variable = variable;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Variable getVariable() {
		return this.variable;
	}
}
