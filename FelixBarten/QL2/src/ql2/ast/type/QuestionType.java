package ql2.ast.type;

import ql2.ASTNode;
import ql2.BaseVisitor;

public abstract class QuestionType extends ASTNode {

	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public abstract String getType();
	

}
