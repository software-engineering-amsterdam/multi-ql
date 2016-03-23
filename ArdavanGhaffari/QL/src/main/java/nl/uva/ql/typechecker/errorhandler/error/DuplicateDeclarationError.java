package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class DuplicateDeclarationError extends Error {
	private static final String ERROR_MESSAGE = "Duplicate declaration error at line {0}: Identifier ''{1}'' has already been declared, with type ''{2}''";
	
	public DuplicateDeclarationError(int line, String identifier, String type) {
		super(MessageFormat.format(ERROR_MESSAGE, Integer.toString(line), identifier, type));
	}
}
