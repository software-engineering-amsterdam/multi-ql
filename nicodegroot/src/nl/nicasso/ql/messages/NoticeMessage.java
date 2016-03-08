package nl.nicasso.ql.messages;

import nl.nicasso.ql.ast.CodeLocation;

public class NoticeMessage extends Message {

	NoticeMessage(String message) {
		super(message);
	}
	
	NoticeMessage(String message, CodeLocation location) {
		super(message, location);
	}
	
	public String getMessage() {
		return "Notice: " + location + " - " + message;
	}

}
