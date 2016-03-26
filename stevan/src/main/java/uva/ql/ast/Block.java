package uva.ql.ast;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import uva.ql.gui.visitors.IActionListenerVisitor;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
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

	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitBlock(this, panel);
	}

	@Override
	public void accept(IActionListenerVisitor visitor) {
		visitor.visitBlock(this);
	}
}
