package uva.ql.typechecker.errors;

import uva.ql.typechecker.abstracts.ErrorWarning;

public class ErrorCyclic extends ErrorWarning {

	public ErrorCyclic(String value, int line, int column) {
		super("error cyclic dependencies between questions: ", value, line, column);
	}

}
