package nl.nicasso.ql.semanticAnalysis.messageHandling;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

	List<Message> messages;
	boolean containsErrors;
	
	public MessageHandler() {
		messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message) {
		if (message.getMessageType().equals("Error")) {
			containsErrors = true;
		}
		messages.add(message);
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public boolean isContainsErrors() {
		return containsErrors;
	}
	
}