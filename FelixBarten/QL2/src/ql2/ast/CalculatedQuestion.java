package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.expression.Expr;
import ql2.ast.type.QuestionType;

public class CalculatedQuestion extends Question {

	private InputQuestion inputQuestion;
	private Expr calculation;
	
	public CalculatedQuestion(InputQuestion input, Expr calculation) {
		this.inputQuestion = input;
		
	}	

	public InputQuestion getInput() {
		return inputQuestion;
	}

	public void setInput(InputQuestion input) {
		this.inputQuestion = input;
	}

	public Expr getCalculation() {
		return calculation;
	}

	public void setCalculation(Expr calculation) {
		this.calculation = calculation;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String getQuestionID() {
		return inputQuestion.getQuestionID();
	}

	public QuestionType getType() {
		return inputQuestion.getType();
	}

}
