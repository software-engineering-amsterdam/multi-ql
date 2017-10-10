package ql2.ast;

import ql2.ASTNode;
import ql2.BaseVisitor;

public class Form extends ASTNode {

	public Form(String result, Block result2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
