package nl.nicasso.ql.semanticAnalysis.messageHandling;

import nl.nicasso.ql.ast.nodes.CodeLocation;

public class Message {
	
	String message;
	// @TODO DO NOT FORGET TO REMOVE THIS
	CodeLocation location = new CodeLocation(0, 0);

	Message(String message) {
		this.message = message;
	}
	
	Message(String message, CodeLocation location) {
		this.message = message;
		this.location = location;
	}
	
	public String getMessage() {
		return location + " - " + message;
	}
	
}
