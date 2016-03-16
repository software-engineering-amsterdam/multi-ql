package nl.nicasso.ql.semanticAnalysis.messageHandling;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

	List<Message> messages;
	
	public MessageHandler() {
		messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	// This one can go I guess?
	public void displayMessages() {
		for (Message message: messages) {
			System.out.println(message.getMessage());
		}
	}
	
	public List<Message> getMessages() {
		return messages;
	}
}
