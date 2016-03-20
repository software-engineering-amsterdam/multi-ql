package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class NoDeclarationError extends Error {
	private static final String ERROR_MESSAGE = "Error at line {0}: Reference to undeclared question identifer ''{1}''";
	
	public NoDeclarationError(int line, String identifier) {
		super(MessageFormat.format(ERROR_MESSAGE, Integer.toString(line), identifier));
	}
}
