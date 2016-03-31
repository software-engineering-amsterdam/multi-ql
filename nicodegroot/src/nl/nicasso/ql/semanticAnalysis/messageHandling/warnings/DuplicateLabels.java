package nl.nicasso.ql.semanticAnalysis.messageHandling.warnings;

import java.text.MessageFormat;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;

public class DuplicateLabels extends WarningMessage {

	private static final String MESSAGE = "Question \"{0}\" has a duplicate label: \"{1}\". {2}";

	private final Identifier identifier;
	private final String label;

	public DuplicateLabels(Identifier identifier, String label) {
		super(identifier.getLocation());
		this.identifier = identifier;
		this.label = label;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getMessage() {
		return MessageFormat.format(DuplicateLabels.MESSAGE, identifier, label, identifier.getLocation());
	}

}