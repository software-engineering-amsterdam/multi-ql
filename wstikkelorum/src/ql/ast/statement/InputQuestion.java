package ql.ast.statement;

import ql.ast.literal.Variable;
import ql.ast.visitor.Visitor;

public class InputQuestion extends Question{

	public InputQuestion(int lineNumber, Variable variable, String str) {
		super(lineNumber, variable, str);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
