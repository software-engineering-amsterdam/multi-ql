package nl.nicasso.ql.semanticAnalysis.messageHandling;

import nl.nicasso.ql.ast.nodes.CodeLocation;

public class WarningMessage extends Message {

	WarningMessage(String message) {
		super(message);
	}
	
	WarningMessage(String message, CodeLocation location) {
		super(message, location);
	}
	
	public String getMessage() {
		return "Error: " + location + " - " + message;
	}

}
