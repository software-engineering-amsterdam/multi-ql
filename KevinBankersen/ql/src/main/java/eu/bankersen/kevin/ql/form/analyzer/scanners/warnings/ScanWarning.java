package eu.bankersen.kevin.ql.form.analyzer.scanners.warnings;

public abstract class ScanWarning {
	private final String message;
	private final int line;

	public ScanWarning(int line, String message) {
		this.line = line;
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("Line %s: %s", line, message);
	}
}
