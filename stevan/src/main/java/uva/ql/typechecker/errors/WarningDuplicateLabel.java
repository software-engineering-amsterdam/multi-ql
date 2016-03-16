package uva.ql.typechecker.errors;

import uva.ql.typechecker.abstracts.ErrorWarning;

public class WarningDuplicateLabel extends ErrorWarning {

	public WarningDuplicateLabel(String value, int line, int column) {
		super("warning duplicate label: ", value, line, column);
	}

}
