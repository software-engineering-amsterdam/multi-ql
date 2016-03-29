package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class CyclicDependencyError extends Error {
	private static final String ERROR_MESSAGE = "There is cyclic dependency between two identifiers: ''{0}'' and ''{1}''";
	
	public CyclicDependencyError(String firstIdentifier, String secondIdentifier) {
		super(MessageFormat.format(ERROR_MESSAGE, firstIdentifier, secondIdentifier));
	}
}
