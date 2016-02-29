package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;


public class StrType extends Type {

	public StrType(CodeFragment fragment) {
		super(fragment, "str");
	}
	
	public StrType() {
		super(new CodeFragment(-1, -1), "str");
	}
	
//	@Override
//	public boolean isStrType() {
//		return true;
//	}
	
//	@Override
//	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
//		return visitor.visit(this);
//	}
	
}