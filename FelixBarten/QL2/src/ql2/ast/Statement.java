package ql2.ast;

import ql2.ASTNode;
import ql2.BaseVisitor;

public class Statement extends ASTNode  {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
