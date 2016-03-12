package uva.ql.ast.expressions.abstracts;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.IExpression;
import uva.ql.interfaces.INodeVisitor;

public abstract class Expression extends Node implements IExpression {
		
	public Expression(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	abstract public EnumType evalType();

	abstract public EnumType getType();

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
