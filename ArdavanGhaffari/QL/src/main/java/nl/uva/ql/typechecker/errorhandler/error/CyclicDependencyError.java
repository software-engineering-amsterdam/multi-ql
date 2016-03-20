package nl.uva.ql.typechecker.errorhandler.error;

import java.text.MessageFormat;

public class CyclicDependencyError extends Error {
	private static final String ERROR_MESSAGE = "Cyclic dependency error at line {0}: there is cyclic dependency between the two identifiers ''{1}'' and ''{2}''";
	
	public CyclicDependencyError(int line, String identifier1, String identifier2) {
		super(MessageFormat.format(ERROR_MESSAGE, Integer.toString(line), identifier1, identifier2));
	}
}
