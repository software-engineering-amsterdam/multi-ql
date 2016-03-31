package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.CodeLocation;

public class CyclomaticDependency extends ErrorMessage {

	private static final String MESSAGE = "Cyclomatic dependency detected. {0}";

	public CyclomaticDependency(CodeLocation location) {
		super(location);
	}

	public String getMessage() {
		return MessageFormat.format(CyclomaticDependency.MESSAGE, location);
	}

}
