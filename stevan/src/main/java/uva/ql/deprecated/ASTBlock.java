package uva.ql.deprecated;

import uva.ql.ast.AST;

public class ASTBlock extends ASTNode implements IASTNode {
	
	ASTBlock(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return ASTNode.BLOCK;
	}

	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visitBlock(this);
	}
}
