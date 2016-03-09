package uva.ql.ast.abstracts;

import uva.ql.ast.EnumType;
import uva.ql.interfaces.INode;

public abstract class Node implements INode {

	private Node parent;
	protected int startLine;
	protected int startColumn;
	
	public Node(Node parent, int startLine, int startColumn) {
		
		this.parent = parent;
		this.startLine = startLine;
		this.startColumn = startColumn;
	}
	
	abstract public EnumType getType();
	
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
}
