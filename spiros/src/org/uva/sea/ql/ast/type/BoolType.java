package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class BoolType extends Type {
	
	public BoolType(CodeFragment fragment) {
		super(fragment, "boolean");
	}
	
	@Override
	public boolean isBoolType() {
		return true;
	}
	
	@Override
	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}
