package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class UndefinedType extends Type {

	public UndefinedType(CodeFragment fragment, String typeName) {
		super(fragment, typeName);
	}
	
	public UndefinedType() {
		// TODO Auto-generated constructor stub
		super(new CodeFragment(-1, -1), "Undefined");
	}

	@Override
	public boolean isUndefined() {
		return true;
	}
	
	@Override
	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}