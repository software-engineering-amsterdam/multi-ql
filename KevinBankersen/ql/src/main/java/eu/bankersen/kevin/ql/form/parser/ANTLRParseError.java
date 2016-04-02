package eu.bankersen.kevin.ql.form.parser;

public class ANTLRParseError {

	private final String message;
	private final int line;

	public ANTLRParseError(int line, String message) {
		this.line = line;
		this.message = message;
	}

	public String toString() {
		return String.format("Line %s: Parse errors: %s", line, message);
	}

}
