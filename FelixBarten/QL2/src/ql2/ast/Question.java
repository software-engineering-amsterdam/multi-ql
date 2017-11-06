package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public abstract class Question extends ASTNode {

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
