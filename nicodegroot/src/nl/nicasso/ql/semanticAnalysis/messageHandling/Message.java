package nl.nicasso.ql.semanticAnalysis.messageHandling;

public abstract class Message {

	protected String messageType;

	public abstract String getMessage();

	public String getMessageType() {
		return messageType;
	}

}