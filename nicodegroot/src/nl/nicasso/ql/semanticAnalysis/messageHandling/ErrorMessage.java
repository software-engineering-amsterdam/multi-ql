package nl.nicasso.ql.semanticAnalysis.messageHandling;

import nl.nicasso.ql.ast.nodes.CodeLocation;

public class ErrorMessage extends Message {

	ErrorMessage(String message) {
		super(message);
	}
	
	ErrorMessage(String message, CodeLocation location) {
		super(message, location);
	}
	
	public String getMessage() {
		return "Error: " + location + " - " + message;
	}

}
