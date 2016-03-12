package uva.ql.ast.abstracts;

import uva.ql.ast.INode;
import uva.ql.visitors.interfaces.INodeVisitor;
import uva.ql.visitors.interfaces.typechecker.IArithmeticOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

public abstract class Node implements INode {

	private Node parent;
	protected int startLine;
	protected int startColumn;
	
	public Node(Node parent, int startLine, int startColumn) {
		
		this.parent = parent;
		this.startLine = startLine;
		this.startColumn = startColumn;
	}
	
	public void setParent(Node n) {
		this.parent = n;
	}
	
	public Node getParent() {
		return parent;
	}

	public int getLine() {
		return startLine;
	}

	public int getColumn() {
		return startColumn;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {}

	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}
	
	@Override
	public void accept(IBinaryOperatorVisitor visitor) {}
	
	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}
}
