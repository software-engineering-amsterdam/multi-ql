package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.visitors.INodeVisitor;

public abstract class Expression extends Node {
		
	public Expression(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	abstract public Type getType();

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
