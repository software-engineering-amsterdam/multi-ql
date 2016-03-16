package uva.ql.typechecker.errors;

import uva.ql.typechecker.abstracts.ErrorWarning;

public class ErrorCondition extends ErrorWarning {

	public ErrorCondition(String value, int line, int column) {
		super("error condition is not of type boolean: ", value, line, column);
	}

}
