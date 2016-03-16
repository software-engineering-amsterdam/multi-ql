package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.CodeLocation;

public class IncompatibleTypes extends Error {
	
	private static final String MESSAGE = "The types in the expression are not compatible. {0}";

	public IncompatibleTypes(CodeLocation location) {
		super(location);
	}
	
	public String getMessage() {
		return MessageFormat.format(IncompatibleTypes.MESSAGE, location);
	}

}
