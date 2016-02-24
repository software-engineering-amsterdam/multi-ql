package ast.typechecker.errorhandler;

import java.text.MessageFormat;

public class DuplicateDeclarationError extends Error {
	private static final String ERROR_MESSAGE = "Duplicate declaration error: Identifier ''{0}'' has already been declared, with type ''{1}''";
	
	public DuplicateDeclarationError(String identifier, String type) {
		super(MessageFormat.format(ERROR_MESSAGE, identifier, type));
	}
}
