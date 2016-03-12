package uva.ql.visitors.typechecker.errors;

import uva.ql.visitors.typechecker.abstracts.ErrorWarning;

public class ErrorUndefinedQuestion extends ErrorWarning {

	public ErrorUndefinedQuestion(String value, int line, int column) {
		super("error reference to undefined question: ", value, line, column);
	}

}
