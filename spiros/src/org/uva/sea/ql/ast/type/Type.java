package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;


// or maybe not extend . . . ?

public abstract class Type extends ASTNode {
	
	private final String typeName;
	
	public Type(CodeFragment fragment, String typeName) {
		super(fragment);
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}
	
	public boolean isUndefined() {
		return false;
	}
	
	public boolean isIntType(){
		return false;
	}
	
	public boolean isBoolType(){
		return false;
	}
	
	public boolean isStrType(){
		return false;
	}
	
	public boolean isDoubleType() {
		return false;
	}
	
	public abstract ASTNode accept(TypeVisitor<ASTNode> visitor);

}
