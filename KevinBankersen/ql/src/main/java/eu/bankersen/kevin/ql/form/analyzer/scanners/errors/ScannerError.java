package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

public abstract class ScannerError {

	private final String message;
	private final int line;

	public ScannerError(int line, String message) {
		this.line = line;
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("Line %s: %s", line, message);
	}

}
