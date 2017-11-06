package ql2.ast.type;

import ql2.BaseVisitor;
import ql2.ast.ASTNode;

public abstract class QuestionType extends ASTNode {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Returns ValueType in String representation.
	 * @return String representation of type
	 */
	public abstract String getType();
	

}
