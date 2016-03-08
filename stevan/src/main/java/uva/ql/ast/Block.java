package uva.ql.ast;

import java.util.ArrayList;
import java.util.List;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

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
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitBlock(this);
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String typeToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitBlock(this);
	}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitBlock(this);
	}

	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitBlock(this);
	}
}
