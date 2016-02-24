package ast.typechecker.errorhandler;

import java.text.MessageFormat;

public class NoDeclarationError extends Error {
	private static final String ERROR_MESSAGE = "Reference to undeclared question identifer ''{0}''";
	
	public NoDeclarationError(String identifier) {
		super(MessageFormat.format(ERROR_MESSAGE, identifier));
	}
}
