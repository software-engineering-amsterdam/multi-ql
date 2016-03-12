package uva.ql.typechecker.errors;

import uva.ql.typechecker.abstracts.ErrorWarning;

public class ErrorOperand extends ErrorWarning {

	public ErrorOperand(String value, int line, int column) {
		super("error operand of invalid type to operators: ", value, line, column);
	}

}
