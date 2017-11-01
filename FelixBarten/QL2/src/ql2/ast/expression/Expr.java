package ql2.ast.expression;

import ql2.BaseVisitor;
import ql2.ast.ASTNode;

public abstract class Expr extends ASTNode  {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
