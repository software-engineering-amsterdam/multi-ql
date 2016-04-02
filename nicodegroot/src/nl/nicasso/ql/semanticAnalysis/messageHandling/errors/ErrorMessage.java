package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;

public abstract class ErrorMessage extends Message {

	protected final CodeLocation location;

	public ErrorMessage(CodeLocation location) {
		this.location = location;
		this.messageType = "Error";
	}

	public CodeLocation getLocation() {
		return location;
	}

}