package ast.typechecker.errorhandler;

import java.text.MessageFormat;

public class TypeMissmatchError extends Error {
	private static final String ERROR_MESSAGE = "Type missmatch error: expected type for ''{0}'' is ''{1}''";
	
	public TypeMissmatchError(String expectedType, String operation) {
		super(MessageFormat.format(ERROR_MESSAGE, operation, expectedType));
	}
}
