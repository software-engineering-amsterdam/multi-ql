package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class InputQuestion extends Question{

	public InputQuestion(String result, String result2, QuestionType result3) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	

}
