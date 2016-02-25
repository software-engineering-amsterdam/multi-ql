package ast.statement;

import ast.literal.Variable;
import ast.visitor.Visitor;

public class InputQuestion extends Question{

	public InputQuestion(int lineNumber, Variable variable, String str) {
		super(lineNumber, variable, str);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
