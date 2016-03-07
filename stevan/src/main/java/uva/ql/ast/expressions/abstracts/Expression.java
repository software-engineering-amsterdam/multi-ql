package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.INodeVisitor;

public abstract class Expression extends Node {
		
	public Expression(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitExp(this);
	}
}
