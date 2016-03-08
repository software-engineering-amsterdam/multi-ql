package nl.nicasso.ql.messages;

import nl.nicasso.ql.ast.CodeLocation;

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
