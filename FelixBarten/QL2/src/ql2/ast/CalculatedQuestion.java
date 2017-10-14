package ql2.ast;

import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class CalculatedQuestion extends Question {

	private InputQuestion input;
	private Expr calculation;
	
	public CalculatedQuestion(InputQuestion result, Expr result2) {
		setInput(result);
		setCalculation(result2);
	}	

	public InputQuestion getInput() {
		return input;
	}

	public void setInput(InputQuestion input) {
		this.input = input;
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

}
