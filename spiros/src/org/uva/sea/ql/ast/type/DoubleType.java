package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class DoubleType extends Type {
	
	public DoubleType(CodeFragment fragment) {
		super(fragment, "double");
	}

	@Override
	public boolean isDoubleType() {
		return true;
	}
	
	@Override
	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}
	
}
