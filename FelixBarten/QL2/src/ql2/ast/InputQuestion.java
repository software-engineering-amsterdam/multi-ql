package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class InputQuestion extends Question{

	private String questionText;
	private String questionID;
	private QuestionType type;
	
	public InputQuestion(String id, String text, QuestionType type) {
		this.questionID = id;
		this.questionText = text;
		this.type = type;
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
		return visitor.visit(this);
	}

}
