package uva.ql.ast;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.visitors.INodeVisitor;

public class Question extends Node {

	private String label;
	private Variable variable;
	
	public Question(Node parent, String label, Variable variable, int startLine, int startColumn) {
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
	
	public Type getType() {
		return variable.getType();
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitQuestion(this);
	}
}
