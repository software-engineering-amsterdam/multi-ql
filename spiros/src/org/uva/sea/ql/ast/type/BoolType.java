package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class BoolType extends Type {
	
	public BoolType(CodeFragment fragment) {
		super(fragment, "boolean");
	}
	
	public BoolType() {
		// TODO Auto-generated constructor stub
		super(new CodeFragment(-1, -1), "boolean");
	}
	
//	@Override
//	public boolean isBoolType() {
//		return true;
//	}
	
//	@Override
//	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
//		return visitor.visit(this);
//	}

}
