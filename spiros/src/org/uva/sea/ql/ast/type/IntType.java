package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class IntType extends Type {

	public IntType(CodeFragment fragment) {
		super(fragment, "int");
	}
	
	public IntType() {
		super(new CodeFragment(-1, -1), "int");
	}
	
//	public boolean isIntType() {
//		return false;
//	}
	
//	@Override
//	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
//		return visitor.visit(this);
//	}
	
}