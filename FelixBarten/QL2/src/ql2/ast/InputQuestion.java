package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class InputQuestion extends Question{

	private String questionText;
	private String questionID; // convert to identityexpr?
	private QuestionType type;
	
	public InputQuestion(String result, String result2, QuestionType result3) {
		setQuestionText(result);
		setQuestionID(result2);
		setType(result3);
	}
	
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	

}
