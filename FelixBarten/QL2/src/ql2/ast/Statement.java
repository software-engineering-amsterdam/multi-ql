package ql2.ast;

import ql2.BaseVisitor;

public abstract class Statement extends ASTNode  {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
