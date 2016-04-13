package uva.ql.ast;

import java.util.Observable;

import uva.ql.visitors.INodeVisitor;

public abstract class Node extends Observable implements INode {

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
	
	public void setLine(int line) {
		this.startLine = line;
	}
	
	public void setColumn(int col) {
		this.startColumn = col;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {}
}
