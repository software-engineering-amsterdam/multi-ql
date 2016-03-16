package uva.ql.typechecker.errors;

import uva.ql.typechecker.abstracts.ErrorWarning;

public class ErrorUndefinedQuestion extends ErrorWarning {

	public ErrorUndefinedQuestion(String value, int line, int column) {
		super("error reference to undefined question: ", value, line, column);
	}

}
