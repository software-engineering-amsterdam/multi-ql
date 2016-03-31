package ql.ast.statement.question;

import ql.ast.literal.Variable;
import ql.ast.type.ValueType;
import ql.ast.visitor.Visitor;

public class InputQuestion extends Question {

	public InputQuestion(int lineNumber, Variable variable, String questionString) {
		super(lineNumber, variable, questionString);
	}
	
	public ValueType getType(){
		return this.getVariable().getType();
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
