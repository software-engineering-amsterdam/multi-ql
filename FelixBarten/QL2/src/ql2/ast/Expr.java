package ql2.ast;

import ql2.ASTNode;
import ql2.BaseVisitor;

public abstract class Expr extends ASTNode  {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
