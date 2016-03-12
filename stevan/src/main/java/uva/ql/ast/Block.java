package uva.ql.ast;

import java.util.ArrayList;
import java.util.List;

import uva.ql.ast.abstracts.Node;
import uva.ql.visitors.interfaces.INodeVisitor;
import uva.ql.visitors.interfaces.typechecker.IArithmeticOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

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
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitBlock(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitBlock(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitBlock(this);
	}

	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitBlock(this);
	}
	
	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitBlock(this);
	}
}
