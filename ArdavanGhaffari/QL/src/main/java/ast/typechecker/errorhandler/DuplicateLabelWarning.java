package ast.typechecker.errorhandler;

import java.text.MessageFormat;

public class DuplicateLabelWarning extends Warning {
	private static final String WARNING_MESSAGE = "Warning at line {0}: duplicate question label: {1}";
	
	public DuplicateLabelWarning(String label, int line) {
		super(MessageFormat.format(WARNING_MESSAGE, Integer.toString(line), label));
	}

}
