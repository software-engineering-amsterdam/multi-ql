package ast.typechecker.errorhandler;

import java.text.MessageFormat;

import ast.model.literal.Identifier;

public class CyclicDependencyError extends Error {
	private static final String ERROR_MESSAGE = "Cyclic dependency error: there is cyclic dependency between the two identifiers ''{0}'' and ''{1}''";
	
	public CyclicDependencyError(Identifier identifier1, Identifier identifier2) {
		super(MessageFormat.format(ERROR_MESSAGE, identifier1, identifier2));
	}
}
