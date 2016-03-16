package nl.uva.sc.ql.messages.errors;

import nl.uva.sc.ql.messages.MyOutputMessages;

public abstract class MyError extends MyOutputMessages {

	public MyError(String message) {
		super(message);
	}
    
}
