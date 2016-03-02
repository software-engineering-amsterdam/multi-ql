package uva.ql.ast.conditionals.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.visitors.INodeVisitor;

public abstract class Condition extends Node {

	public Condition(Node parent, int startLine, int startColumn) {
		super(null, startLine, startColumn);
	}
	
	abstract public Type getType();

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitCondition(this);
	}
}
