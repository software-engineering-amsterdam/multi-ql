package nl.nicasso.ql.semanticAnalysis.messageHandling;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.ErrorMessage;
import nl.nicasso.ql.semanticAnalysis.messageHandling.notifications.NotificationMessage;
import nl.nicasso.ql.semanticAnalysis.messageHandling.warnings.WarningMessage;

public class MessageHandler {

	private List<ErrorMessage> errorMessages;
	private List<WarningMessage> warningMessages;
	private List<NotificationMessage> notificationMessages;
	
	public MessageHandler() {
		errorMessages = new ArrayList<ErrorMessage>();
		warningMessages = new ArrayList<WarningMessage>();
		notificationMessages = new ArrayList<NotificationMessage>();
	}
	
	public void addErrorMessage(ErrorMessage message) {
		errorMessages.add(message);
	}
	
	public void addWarningMessage(WarningMessage message) {
		warningMessages.add(message);
	}
	
	public void addNotificationMessage(NotificationMessage message) {
		notificationMessages.add(message);
	}
	
	public List<ErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public List<WarningMessage> getWarningMessages() {
		return warningMessages;
	}

	public List<NotificationMessage> getNotificationMessages() {
		return notificationMessages;
	}
	
	public List<Message> getAllMessages() {
		List<Message> allMessages = new ArrayList<Message>();
		allMessages.addAll(errorMessages);
		allMessages.addAll(warningMessages);
		allMessages.addAll(notificationMessages);
		
		return allMessages;
	}

	public boolean containsErrors() {
		return errorMessages.size() > 0;
	}
	
}