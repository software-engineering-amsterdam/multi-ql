package nl.nicasso.ql.semanticAnalysis.messageHandling;

import nl.nicasso.ql.ast.nodes.CodeLocation;

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
