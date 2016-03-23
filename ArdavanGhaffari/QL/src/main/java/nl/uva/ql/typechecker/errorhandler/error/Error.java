package nl.uva.ql.typechecker.errorhandler.error;

public class Error {
	private String message;

	public String getMessage() {
		return message;
	}
	
	public Error(String message) {
		this.message = message;
	}
}
