package uva.ql.ast;

import java.util.ArrayList;

import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;

public class ASTBlock extends ASTNode implements IASTNode {

	private ArrayList<Object> store = new ArrayList<Object>(0);
	
	ASTBlock(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return ASTNode.BLOCK;
	}

	public void addChild(ASTNode node) {
		this.store.add(node);
	}
	
	public int size() {
		return this.store.size();
	}
	
	public ASTNode get(int index) {
		return (ASTNode) this.store.get(index);
	}

	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
