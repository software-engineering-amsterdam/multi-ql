package uva.ql.ast;

import java.util.ArrayList;

import uva.ql.interfaces.INode;

public abstract class ANode implements INode {

	final AST ast;
	private int nodeType = 0;	
	private ANode parent = null;
	private ArrayList<Object> store = new ArrayList<Object>(0);
	private int startLine = 0;
	private int startColumn = 0;
	
	ANode(AST ast) {
		if (ast == null) {
			throw new IllegalArgumentException();
		}
		
		this.ast = ast;
		setNodeType(getNodeType0());
	}
	
	protected abstract int getNodeType0();
	
	public final int getNodeType() {
		return this.nodeType;
	}
	
	private void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	
	public int size() {
		return this.store.size();
	}
	
	public ANode get(int index) {
		return (ANode) this.store.get(index);
	}
	
	public void addChild(ANode node) {
		this.store.add(node);
	}

	public ANode getParent() {
		return parent;
	}

	public void setParent(ANode parent) {
		this.parent = parent;
	}

	public int getLine() {
		return startLine;
	}

	public void setLine(int startLine) {
		this.startLine = startLine;
	}

	public int getColumn() {
		return startColumn;
	}

	public void setColumn(int startColumn) {
		this.startColumn = startColumn;
	}

}
