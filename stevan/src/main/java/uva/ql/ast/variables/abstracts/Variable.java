package uva.ql.ast.variables.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.INodeVisitor;

public abstract class Variable extends Expression {

	private String name;
	
	public Variable(Node parent, String name, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	abstract public Type getType();

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitVar(this);
	}
}
