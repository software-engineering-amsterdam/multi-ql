package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class TypeMissmatchError extends Error {
	private static final String ERROR_MESSAGE = "Type missmatch error at line {0}: expected type for ''{1}'' is ''{2}''";
	
	public TypeMissmatchError(int line, String operation, String expectedType) {
		super(MessageFormat.format(ERROR_MESSAGE, Integer.toString(line), operation, expectedType));
	}
}
