package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class OperationTypeMissmatchError extends Error {
	private static final String ERROR_MESSAGE = "Type missmatch error at line {0}: invalid operand type for ''{1}'' operation";
	
	public OperationTypeMissmatchError(int line, String operation) {
		super(MessageFormat.format(ERROR_MESSAGE, Integer.toString(line), operation));
	}
}