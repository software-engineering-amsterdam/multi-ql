package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;

public class NonExistantQuestion extends Error {
	
	private static final String MESSAGE = "Identifer \"{0}\" does not exist. {1}";

	private final Identifier identifier;

	public NonExistantQuestion(Identifier identifier) {
		super(identifier.getLocation());
		this.identifier = identifier;
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	public String getMessage() {
		return MessageFormat.format(NonExistantQuestion.MESSAGE, identifier.getValue(), identifier.getLocation());
	}

}
