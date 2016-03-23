package ql.ast.statement;

import ql.ast.literal.Variable;
import ql.ast.types.ValueType;
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
