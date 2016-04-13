package uva.ql.ast;

import java.util.ArrayList;
import java.util.List;

import uva.ql.visitors.INodeVisitor;

public class Block extends Node {

	protected List<Node> children = new ArrayList<Node>(0);
	
	public Block(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	public void add(Node n) {
		this.children.add(n);
	}
	
	public Node get(int i) {
		return this.children.get(i);
	}
	
	public int size() {
		return this.children.size();
	}
	
	public List<Node> children() {
		return this.children;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitBlock(this);
	}
}
