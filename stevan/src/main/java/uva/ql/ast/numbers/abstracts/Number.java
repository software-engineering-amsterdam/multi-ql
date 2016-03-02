package uva.ql.ast.numbers.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.INodeVisitor;

public abstract class Number extends Expression {

	public Number(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitNum(this);
	}
}
