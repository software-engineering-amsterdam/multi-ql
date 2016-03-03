package uva.ql.ast;

import java.util.List;
import uva.ql.ast.abstracts.Node;
import uva.ql.visitors.INodeVisitor;

public class Block extends Node {

	private List<Node> children;
	
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

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitBlock(this);
	}
}
