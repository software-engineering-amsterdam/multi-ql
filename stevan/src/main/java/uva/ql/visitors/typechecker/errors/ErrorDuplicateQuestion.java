package uva.ql.visitors.typechecker.errors;

import uva.ql.visitors.typechecker.abstracts.ErrorWarning;

public class ErrorDuplicateQuestion extends ErrorWarning {

	public ErrorDuplicateQuestion(String value, int line, int column) {
		super("error duplicate question declarations with different types: ", value, line, column);
	}

}
