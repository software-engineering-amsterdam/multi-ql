package uva.ql.ast.variables.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.gui.visitors.IGUIVisitor;

public abstract class Variable extends Expression {

	private String name;
	
	public Variable(Node parent, String name, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLine(int line) {
		this.startLine = line;
	}
	
	public void setColumn(int col) {
		this.startColumn = col;
	}

	public void accept(IGUIVisitor visitor) {
		visitor.visitVar(this);
	}
}
