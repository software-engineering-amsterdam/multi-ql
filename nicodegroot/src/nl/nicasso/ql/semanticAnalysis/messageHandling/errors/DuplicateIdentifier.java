package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;

public class DuplicateIdentifier extends ErrorMessage {

	private static final String MESSAGE = "\"{0}\" already exists with a different type. {1}";

	private final Identifier identifier;

	public DuplicateIdentifier(Identifier identifier) {
		super(identifier.getLocation());
		this.identifier = identifier;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getMessage() {
		return MessageFormat.format(DuplicateIdentifier.MESSAGE, identifier, identifier.getLocation());
	}

}