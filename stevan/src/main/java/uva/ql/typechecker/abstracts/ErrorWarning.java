package uva.ql.typechecker.abstracts;

public abstract class ErrorWarning {
	
	private final String message;
	private final String value;
	private final int line;
	private final int column;
	
	public ErrorWarning(String message, String value, int line, int column) {
		this.message = message;
		this.value = value;
		this.line = line;
		this.column = column;
	}

	public String getMessage() {
		return this.message + this.value + " - " + this.line + ", " + this.column;
	}
}
