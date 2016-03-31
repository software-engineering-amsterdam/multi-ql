package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.Type;

public class IncompatibleTypes extends ErrorMessage {

	private static final String MESSAGE = "The types in the expression are not compatible, a {0} type is expected but not given. {1}";

	private Type expectedType;

	public IncompatibleTypes(CodeLocation location, Type expectedType) {
		super(location);
		this.expectedType = expectedType;
	}

	public Type getExpectedType() {
		return expectedType;
	}

	public String getMessage() {
		return MessageFormat.format(IncompatibleTypes.MESSAGE, expectedType.getTypeName(), location);
	}

}