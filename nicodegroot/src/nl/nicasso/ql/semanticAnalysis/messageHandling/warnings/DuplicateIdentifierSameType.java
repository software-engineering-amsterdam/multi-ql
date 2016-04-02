package nl.nicasso.ql.semanticAnalysis.messageHandling.warnings;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;

public class DuplicateIdentifierSameType extends WarningMessage {

	private static final String MESSAGE = "\"{0}\" already exists with the same type. {1}";

	private final Identifier identifier;

	public DuplicateIdentifierSameType(Identifier identifier) {
		super(identifier.getLocation());
		this.identifier = identifier;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getMessage() {
		return MessageFormat.format(DuplicateIdentifierSameType.MESSAGE, identifier, identifier.getLocation());
	}

}