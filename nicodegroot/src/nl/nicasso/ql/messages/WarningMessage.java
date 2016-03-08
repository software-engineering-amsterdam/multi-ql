package nl.nicasso.ql.messages;

import nl.nicasso.ql.ast.CodeLocation;

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
