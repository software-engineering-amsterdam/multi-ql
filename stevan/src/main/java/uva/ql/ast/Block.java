package uva.ql.ast;

import uva.ql.interfaces.INodeVisitor;

public class Block extends ANode {

	Block(AST ast) {
		super(ast);
	}

	@Override
	protected int getNodeType0() {
		return ANode.BLOCK;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitBlock(this);
	}
}
