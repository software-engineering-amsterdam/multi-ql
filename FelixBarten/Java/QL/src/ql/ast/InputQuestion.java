package ql.ast;

import ql.BaseVisitor;

public class InputQuestion extends Question {

	public InputQuestion(String q_text, String identity, QuestionType typ) {
		super(q_text, identity, typ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
