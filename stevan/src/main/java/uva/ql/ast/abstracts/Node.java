package uva.ql.ast.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.INode;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.INodeVisitor;

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

	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {}
	
}
