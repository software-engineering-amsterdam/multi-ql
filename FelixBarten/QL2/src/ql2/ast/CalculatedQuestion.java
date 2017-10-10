package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class CalculatedQuestion extends Question {

	public CalculatedQuestion(InputQuestion result, Statement result2) {
		// TODO Auto-generated constructor stub
	}

	public CalculatedQuestion(InputQuestion result, Expr result2) {
		// TODO Auto-generated constructor stub
	}

	public CalculatedQuestion(String result, String result2, QuestionType result3, Expr result4) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
