package nl.nicasso.ql.semanticAnalysis.messageHandling.notifications;

import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;

public abstract class NotificationMessage extends Message {
		
	public NotificationMessage() {
		this.messageType = "Notification";
	}
	
}
