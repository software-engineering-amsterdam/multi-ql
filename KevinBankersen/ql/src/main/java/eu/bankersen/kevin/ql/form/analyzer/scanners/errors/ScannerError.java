package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

public abstract class ScannerError {

	private final String message;
	private final int line;

	public ScannerError(int line, String message) {
		this.line = line;
		this.message = message;
	}

	public String toString() {
		return line == 0 ? message : String.format("Line %s: %s", line, message);
	}

}
