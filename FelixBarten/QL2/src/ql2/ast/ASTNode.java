package ql2.ast;

import ql2.BaseVisitor;

public abstract class ASTNode {

	public abstract <T> T accept(BaseVisitor<T> visitor);
	
}
